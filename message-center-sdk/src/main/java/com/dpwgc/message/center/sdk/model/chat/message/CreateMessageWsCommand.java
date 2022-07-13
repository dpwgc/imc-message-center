package com.dpwgc.message.center.sdk.model.chat.message;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CreateMessageWsCommand {

    /**
     * 消息所属应用id
     */
    private String appId;

    /**
     * 消息所属群组id
     */
    private String groupId;

    /**
     * 发送该条消息的用户id
     */
    private String userId;

    /**
     * 消息主体内容
     */
    private String content;

    /**
     * 消息类型（自定义）
     */
    private Integer type;
}
