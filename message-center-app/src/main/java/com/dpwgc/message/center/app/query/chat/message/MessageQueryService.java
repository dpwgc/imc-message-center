package com.dpwgc.message.center.app.query.chat.message;

import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import com.dpwgc.message.center.sdk.model.chat.message.MessagePageDTO;

import java.util.List;

public interface MessageQueryService {

    MessagePageDTO findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    MessagePageDTO findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    MessagePageDTO findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    List<MessageDTO> findBeforeByMessageId(String appId, String groupId, String messageId, Integer pageSize);
    List<MessageDTO> findAfterByMessageId(String appId, String groupId, String messageId, Integer pageSize);
}
