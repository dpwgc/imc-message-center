package com.dpwgc.message.center.infrastructure.repository.chat;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.assembler.MessagePOAssembler;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.infrastructure.dal.chat.mapper.MessageMapper;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageRepositoryImpl implements MessageRepository {

    @Resource
    MessageMapper messageMapper;

    @Override
    public boolean save(Message message) {
        MessagePO messagePO = MessagePOAssembler.INSTANCE.assemblerMessagePO(message);
        int i = messageMapper.insert(messagePO);
        return i > 0;
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
