package com.dpwgc.messagecenterdomain.chat.message;

import org.springframework.stereotype.Component;

/**
 * @author cah
 * @description TODO
 * @date 2021/11/3 10:09 上午
 */
@Component
public class MessageFactory {

    public Message create(String appId,String groupId, String userId, String content) {
        return new Message().create(appId,groupId, userId, content);
    }
}
