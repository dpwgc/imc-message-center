package com.dpwgc.message.center.sdk.model.chat.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMessageCommand {

    /**
     * 消息所属应用id
     */
    @NotNull
    private String appId;

    /**
     * 消息所属群组id
     */
    @NotNull
    private String groupId;

    /**
     * 发送该条消息的用户id
     */
    @NotNull
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
