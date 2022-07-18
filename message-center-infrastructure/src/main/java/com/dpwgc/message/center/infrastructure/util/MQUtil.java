package com.dpwgc.message.center.infrastructure.util;

import com.dpwgc.message.center.infrastructure.component.RedisClient;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MQUtil {

    @Resource
    RedisClient redisClient;

    public void send(String key, MessageDTO messageDTO) throws JsonProcessingException {

        //将MessageDTO对象转为json字符串
        String jsonStr = JsonUtil.toJson(messageDTO);

        //封装成redis stream的消息格式
        Map<String,Object> msg = new HashMap<>();
        msg.put(key,jsonStr);

        redisClient.addStream(key,msg);
    }
}
