package com.dpwgc.message.center.app.command.chat.service.message.impl;

import com.dpwgc.message.center.app.command.chat.service.message.MessageService;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageFactory;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.util.SnowUtil;
import com.dpwgc.message.center.sdk.command.chat.message.MessageCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    SnowUtil snowUtil;

    @Resource
    MessageRepository messageRepository;

    @Override
    public String createMessage(MessageCommand command) {
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(snowUtil.nextIdString(),command.getAppId(),command.getGroupId(),command.getUserId(),command.getContent(),command.getCreateTime());

        if (messageRepository.save(message)) {
            return message.getMessageId();
        }
        return "";
    }
}
