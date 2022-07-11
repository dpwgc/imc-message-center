package com.dpwgc.message.center.infrastructure.repository.chat;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRepositoryImpl implements MessageRepository {

    @Override
    public boolean save(Message message) {
        return false;
    }
    @Override
    public boolean recall(String id) {
        return false;
    }
    @Override
    public List<MessageDTO> findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {
        return null;
    }
    @Override
    public List<MessageDTO> findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {
        return null;
    }
    @Override
    public List<MessageDTO> findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {
        return null;
    }

}
