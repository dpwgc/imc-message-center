package com.dpwgc.message.center.infrastructure.dal.chat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("imc_chat_message")
public class MessagePO {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 消息id
     */
    private String messageId;

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
     * 消息创建时间戳（毫秒级）
     */
    private Long createTime;

    /**
     * 消息撤回时间戳（毫秒级）
     */
    private Long recallTime;

    /**
     * 消息撤回原因（谁撤回了消息）
     */
    private String recallCause;

    /**
     * 消息当前状态（1-正常，0-已撤回）
     */
    private Integer status;

    /**
     * 消息类型（自定义）
     */
    private Integer type;
}
