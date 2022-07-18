package com.dpwgc.message.center.infrastructure.util;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.infrastructure.component.RedisClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MQUtil {

    @Resource
    RedisClient redisClient;

    @Value("${spring.application.name}")
    private String applicationName;

    public void send(Message message) throws JsonProcessingException {

        //将MessageDTO对象转为json字符串
        String jsonStr = JsonUtil.toJson(message);

        //封装成redis stream的消息格式
        Map<String,Object> msg = new HashMap<>();
        msg.put("message",jsonStr);

        //向stream发送消息，stream名称：applicationName（imc-message-center）
        redisClient.addStream(applicationName,msg);
    }
}
