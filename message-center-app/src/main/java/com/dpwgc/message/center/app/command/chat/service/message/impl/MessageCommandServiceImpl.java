package com.dpwgc.message.center.app.command.chat.service.message.impl;

import com.alibaba.fastjson.JSON;
import com.dpwgc.message.center.app.command.chat.assembler.MessageAssembler;
import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
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
public class MessageCommandServiceImpl implements MessageCommandService {

    @Resource
    SnowUtil snowUtil;

    @Resource
    RedisUtil redisUtil;

    @Resource
    MessageRepository messageRepository;

    @Resource
    MessageAssembler messageAssembler;


    @Override
    public boolean createMessage(CreateMessageWsCommand command,String appId,String groupId,String userId) {

        //创建Message对象
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(snowUtil.nextIdString(),appId,groupId,userId,command.getContent(),command.getType());

        //成功插入数据层
        if (messageRepository.save(message)) {

            //构建MessageDTO对象
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(message);

            //将MessageDTO对象转为json字符串
            String jsonStr = JSON.parse(messageDTO.toString()).toString();

            //在redis管道中发布消息
            redisUtil.pub("broadcast-".concat(appId),jsonStr);

            LogUtil.info("save user message: ".concat(jsonStr));

            return true;
        }
        return false;
    }

    public boolean recallMessage(String messageId,String recallCause) {
        Message message = messageRepository.recall(messageId,recallCause);

        if (message != null) {
            //构建MessageDTO对象
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(message);

            //将MessageDTO对象转为json字符串
            String jsonStr = JSON.parse(messageDTO.toString()).toString();

            //在redis管道中广播撤回信息，告知群组中的所有在线成员该消息已被撤回
            redisUtil.pub("broadcast-".concat(messageDTO.getAppId()),jsonStr);

            LogUtil.info("recall message: ".concat(jsonStr).concat("/ cause: ").concat(recallCause));
            return true;
        }

        return false;
    }
}
