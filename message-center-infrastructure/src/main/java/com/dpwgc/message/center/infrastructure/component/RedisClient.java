package com.dpwgc.message.center.infrastructure.component;

import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Redis工具类
 */
@Component
public class RedisClient {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 用来判断key是否存在
     */
    public boolean hasKey(String key){
        if(key==null){
            return false;
        }else{
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        }

    }

    //====================pub/sub======================

    /**
     * 发布消息
     * @param channel 管道/主题名称
     * @param message 消息主体信息
     */
    public void pub(String channel, String message) {

        redisTemplate.convertAndSend(channel, message);
    }

    //====================stream======================

    /**
     * 删除stream中的消息（确认消费）
     */
    public void delField(String key, String fieldId){
        redisTemplate.opsForStream().delete(key,fieldId);
    }

    /**
     * 往stream中发送消息
     */
    public RecordId addStream(String key, Map<String,Object> message){
        return redisTemplate.opsForStream().add(key, message);	//返回增加后的id
    }

    public void addGroup(String key, String groupName){
        redisTemplate.opsForStream().createGroup(key,groupName);
    }

}
