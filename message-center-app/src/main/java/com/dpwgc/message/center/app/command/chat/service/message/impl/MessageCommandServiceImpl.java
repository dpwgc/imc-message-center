package com.dpwgc.message.center.app.command.chat.service.message.impl;

import com.dpwgc.message.center.app.assembler.MessageAssembler;
import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageFactory;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.util.BroadcastUtil;
import com.dpwgc.message.center.infrastructure.util.IdGenUtil;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.infrastructure.util.MQUtil;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageCommand;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageWsCommand;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

        String sid = idGenUtil.nextIdString();

        //创建Message对象
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(sid,command.getAppId(),command.getGroupId(),command.getUserId(),command.getContent(),command.getType());

        try {
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(message);
            mqUtil.send(sid,messageDTO);
            broadcastUtil.broadcast(messageDTO);
            return true;
        } catch (Exception e) {
            LogUtil.error(e.toString());
            return false;
        }
    }

    @Override
    public boolean createMessage(CreateMessageCommand command) {

        String sid = idGenUtil.nextIdString();

        //创建Message对象
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(sid,command.getAppId(),command.getGroupId(),command.getUserId(),command.getContent(),command.getType());

        try {
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(message);
            mqUtil.send(sid,messageDTO);
            broadcastUtil.broadcast(messageDTO);
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
                return broadcastUtil.broadcast(messageAssembler.assembleMessageDTO(message));
            }
        }

        return false;
    }
}
