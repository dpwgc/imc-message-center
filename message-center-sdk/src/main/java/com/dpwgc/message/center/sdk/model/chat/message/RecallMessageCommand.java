package com.dpwgc.message.center.sdk.model.chat.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecallMessageCommand {

    /**
     * 要撤回的消息id
     */
    @NotNull
    private String messageId;

    /**
     * 撤回的原因（或谁撤回了消息）
     */
    @NotNull
    private String recallCause;
}
