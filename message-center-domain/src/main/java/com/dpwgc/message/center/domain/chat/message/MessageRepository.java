package com.dpwgc.message.center.domain.chat.message;

import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;

import java.util.List;

public interface MessageRepository {
    boolean save(MessageDTO messageDTO);
    boolean recall(String id);
    List<MessageDTO> findByGroupId(String appId, String groupId, Integer pageNum, Integer pageSize);
    List<MessageDTO> findByUserId(String appId, String userId, Integer pageNum, Integer pageSize);
    List<MessageDTO> findByGroupIdAndUserId(String appId, String groupId, String userId, Integer pageNum, Integer pageSize);
}
