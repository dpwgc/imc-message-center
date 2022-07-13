package com.dpwgc.message.center.sdk.model.chat.message;

import lombok.Data;

@Data
public class RecallMessageCommand {

    /**
     * 要撤回的消息id
     */
    private String messageId;

    /**
     * 撤回的原因（或谁撤回了消息）
     */
    private String recallCause;
}
