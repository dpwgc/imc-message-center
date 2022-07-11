package com.dpwgc.message.center.domain.chat.message;

import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;

import java.util.List;

public interface MessageRepository {
    boolean save(Message message);
    boolean recall(String id);
    List<MessageDTO> findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    List<MessageDTO> findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    List<MessageDTO> findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
}
