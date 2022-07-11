package com.dpwgc.message.center.infrastructure.repository.chat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.assembler.MessagePOAssembler;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.infrastructure.dal.chat.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageRepositoryImpl implements MessageRepository {

    @Resource
    MessageMapper messageMapper;

    @Resource
    MessagePOAssembler messagePOAssembler;

    @Override
    public boolean save(Message message) {
        MessagePO messagePO = messagePOAssembler.assemblerMessagePO(message);
        return messageMapper.insert(messagePO) > 0;
    }
    @Override
    public boolean recall(String messageId) {

        //获取消息
        QueryWrapper<MessagePO> queryOrder = new QueryWrapper<>();
        queryOrder.eq("message_id",messageId);
        queryOrder.eq("status",1);
        MessagePO messagePO = messageMapper.selectOne(queryOrder);

        //如果查不到此消息
        if(messagePO == null){
            return false;
        }

        //将该消息设为撤回状态
        messagePO.setStatus(0);

        //更新订单
        return messageMapper.update(messagePO,queryOrder) > 0;
    }
}
