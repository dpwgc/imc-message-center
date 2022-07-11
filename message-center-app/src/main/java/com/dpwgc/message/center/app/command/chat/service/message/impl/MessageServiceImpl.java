package com.dpwgc.message.center.app.command.chat.service.message.impl;

import com.alibaba.fastjson.JSONObject;
import com.dpwgc.message.center.app.command.chat.service.message.MessageService;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageFactory;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.infrastructure.util.RedisUtil;
import com.dpwgc.message.center.infrastructure.util.SnowUtil;
import com.dpwgc.message.center.sdk.command.chat.message.CreateMessageWsCommand;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    SnowUtil snowUtil;

    @Resource
    RedisUtil redisUtil;

    @Resource
    MessageRepository messageRepository;

    @Override
    public boolean createMessage(CreateMessageWsCommand command,String appId,String groupId,String userId) {

        //构建MessageDTO对象
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setAppId(appId);
        messageDTO.setGroupId(groupId);
        messageDTO.setUserId(userId);

        messageDTO.setContent(command.getContent());
        messageDTO.setType(command.getType());

        //创建Message对象
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(snowUtil.nextIdString(),messageDTO.getAppId(),messageDTO.getGroupId(),messageDTO.getUserId(),messageDTO.getContent());

        //成功插入数据层
        if (messageRepository.save(message)) {

            messageDTO.setMessageId(message.getMessageId());
            messageDTO.setCreateTime(message.getCreateTime());
            messageDTO.setStatus(message.getStatus());

            //将MessageDTO对象转为json字符串
            String jsonStr = JSONObject.toJSON(messageDTO).toString();

            //在redis管道中发布消息
            redisUtil.pub("broadcast-".concat(appId),jsonStr);

            LogUtil.info("save user message: ".concat(jsonStr));

            return true;
        }
        return false;
    }
}
