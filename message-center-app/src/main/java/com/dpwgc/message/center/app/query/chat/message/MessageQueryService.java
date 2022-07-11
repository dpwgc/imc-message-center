package com.dpwgc.message.center.app.query.chat.message;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;

import java.util.List;

public interface MessageQueryService {

    List<MessageDTO> findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    List<MessageDTO> findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    List<MessageDTO> findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
}
