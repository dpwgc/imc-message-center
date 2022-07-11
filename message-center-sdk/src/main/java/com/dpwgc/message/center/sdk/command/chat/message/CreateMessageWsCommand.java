package com.dpwgc.message.center.sdk.command.chat.message;

import lombok.Data;

@Data
public class CreateMessageWsCommand {

    /**
     * 消息主体内容
     */
    private String content;

    /**
     * 消息类型（自定义）
     */
    private Integer type;
}
