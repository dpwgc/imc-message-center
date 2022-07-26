package com.dpwgc.message.center.app.command.chat.message.impl;

import com.dpwgc.message.center.app.assembler.MessageAssembler;
import com.dpwgc.message.center.app.command.chat.message.MessageCommandService;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageFactory;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.util.BroadcastUtil;
import com.dpwgc.message.center.infrastructure.util.IdGenUtil;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.infrastructure.util.MQUtil;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageCommand;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageWsCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    @Resource
    MQUtil mqUtil;

    @Resource
    IdGenUtil idGenUtil;

    @Resource
    BroadcastUtil broadcastUtil;

    @Resource
    MessageRepository messageRepository;

    @Resource
    MessageAssembler messageAssembler;

    @Override
    public boolean createMessage(CreateMessageWsCommand command) {

        //创建Message对象
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(idGenUtil.nextIdString(),command.getAppId(),command.getGroupId(),command.getUserId(),command.getContent(),command.getType());

        try {
            //发送消息至MQ
            mqUtil.send(message);
            //广播消息
            broadcastUtil.send(messageAssembler.assembleMessageDTO(message));
            return true;
        } catch (Exception e) {
            LogUtil.error(e.toString());
            return false;
        }
    }

    @Override
    public boolean createMessage(CreateMessageCommand command) {

        //创建Message对象
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(idGenUtil.nextIdString(),command.getAppId(),command.getGroupId(),command.getUserId(),command.getContent(),command.getType());

        try {
            //发送消息至MQ
            mqUtil.send(message);
            //广播消息
            broadcastUtil.send(messageAssembler.assembleMessageDTO(message));
            return true;
        } catch (Exception e) {
            LogUtil.error(e.toString());
            return false;
        }
    }

    public boolean recallMessage(String messageId,String recallCause) {
        Message message = messageRepository.recall(messageId,recallCause);

        if (message != null) {
            if (message.getStatus() == 0) {
                //广播消息
                return broadcastUtil.send(messageAssembler.assembleMessageDTO(message));
            }
        }

        return false;
    }
}
