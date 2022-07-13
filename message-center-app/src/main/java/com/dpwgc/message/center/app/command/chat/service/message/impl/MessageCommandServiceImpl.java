package com.dpwgc.message.center.app.command.chat.service.message.impl;

import com.dpwgc.message.center.app.assembler.MessageAssembler;
import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageFactory;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.util.BroadcastUtil;
import com.dpwgc.message.center.infrastructure.util.IdGenUtil;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageCommand;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageWsCommand;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

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

        //成功插入数据层
        if (messageRepository.save(message)) {
            //广播消息
            return broadcastUtil.broadcast(messageAssembler.assembleMessageDTO(message));
        }
        return false;
    }

    @Override
    public boolean createMessage(CreateMessageCommand command) {

        //创建Message对象
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(idGenUtil.nextIdString(),command.getAppId(),command.getGroupId(),command.getUserId(),command.getContent(),command.getType());

        //成功插入数据层
        if (messageRepository.save(message)) {
            //广播消息
            return broadcastUtil.broadcast(messageAssembler.assembleMessageDTO(message));
        }
        return false;
    }

    public boolean recallMessage(String messageId,String recallCause) {
        Message message = messageRepository.recall(messageId,recallCause);

        if (message != null) {

            //构建MessageDTO对象
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(message);

            //广播消息
            return broadcastUtil.broadcast(messageDTO);
        }

        return false;
    }
}
