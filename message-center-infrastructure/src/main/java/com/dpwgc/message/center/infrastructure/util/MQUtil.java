package com.dpwgc.message.center.infrastructure.util;

import com.dpwgc.message.center.infrastructure.component.RedisClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class MQUtil {

    @Resource
    RedisClient redisClient;

    public void send(String key, Map<String,Object> message) {
        redisClient.addStream(key,message);
    }
}
