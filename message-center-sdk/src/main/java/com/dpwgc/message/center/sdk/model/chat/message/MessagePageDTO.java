package com.dpwgc.message.center.sdk.model.chat.message;

import lombok.Data;

import java.util.List;

@Data
public class MessagePageDTO {
    /**
     * 指定时间段内的消息总数
     */
    private Long total;
    /**
     * 当前分页内的消息列表
     */
    private List<MessageDTO> pageMessageList;
}
