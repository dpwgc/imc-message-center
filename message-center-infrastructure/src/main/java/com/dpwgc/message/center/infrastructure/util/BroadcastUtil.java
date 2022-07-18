package com.dpwgc.message.center.infrastructure.util;

import com.dpwgc.message.center.infrastructure.component.RedisClient;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BroadcastUtil {

    @Resource
    RedisClient redisClient;

    /**
     * 广播消息
     * @param messageDTO 消息数据
     * @return boolean
     */
    public boolean send(MessageDTO messageDTO) {

        try {
            //将MessageDTO对象转为json字符串
            String jsonStr = JsonUtil.toJson(messageDTO);

            //在redis管道中发布消息
            redisClient.pub("broadcast-".concat(messageDTO.getAppId()),jsonStr);

            return true;

        } catch (Exception e) {
            LogUtil.error(e.toString());
            return false;
        }
    }
}
