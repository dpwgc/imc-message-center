package com.dpwgc.message.center.infrastructure.util;

import com.alibaba.fastjson.JSON;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BroadcastUtil {

    @Resource
    RedisUtil redisUtil;

    /**
     * 广播消息
     * @param messageDTO 消息数据
     * @return boolean
     */
    public boolean broadcast(MessageDTO messageDTO) {

        try {
            //将MessageDTO对象转为json字符串
            String jsonStr = JSON.parse(messageDTO.toString()).toString();

            //在redis管道中发布消息
            redisUtil.pub("broadcast-".concat(messageDTO.getAppId()),jsonStr);

            LogUtil.info("broadcast message: ".concat(jsonStr));

            return true;

        } catch (Exception e) {
            LogUtil.error(e.toString());
            return false;
        }
    }
}
