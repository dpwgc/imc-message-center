package com.dpwgc.message.center.domain.chat.message;

import org.springframework.stereotype.Component;

/**
 * @author cah
 * @description TODO
 * @date 2021/11/3 10:09 上午
 */
@Component
public class MessageFactory {

    public Message create(String messageId, String appId, String groupId, String userId, String content, Integer type) {
        return new Message().create(messageId,appId,groupId, userId, content, type);
    }
}
