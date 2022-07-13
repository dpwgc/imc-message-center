package com.dpwgc.message.center.infrastructure.repository.chat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.assembler.MessagePOAssembler;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.infrastructure.dal.chat.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageRepositoryImpl implements MessageRepository {

    @Resource
    MessageMapper messageMapper;

    @Value("${chat.message.recallTimeLimit}")
    private Long recallTimeLimit;

    @Override
    public boolean save(Message message) {
        MessagePO messagePO = MessagePOAssembler.INSTANCE.assemblerMessagePO(message);
        return messageMapper.insert(messagePO) > 0;
    }
    @Override
    public Message recall(String messageId,String recallCause) {

        //获取消息
        QueryWrapper<MessagePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("message_id",messageId);
        queryWrapper.eq("status",1);
        MessagePO messagePO = messageMapper.selectOne(queryWrapper);

        //如果查不到此消息
        if(messagePO == null){
            return null;
        }

        //判断是否超过撤回时限
        if (System.currentTimeMillis() > messagePO.getCreateTime() + recallTimeLimit) {
            return null;
        }

        //将该消息设为撤回状态
        messagePO.setStatus(0);
        messagePO.setRecallTime(System.currentTimeMillis());
        messagePO.setRecallCause(recallCause);

        //如果更新成功
        if (messageMapper.update(messagePO,queryWrapper) > 0) {
            //去掉消息主体内容，再返回
            messagePO.setContent("");
            return MessagePOAssembler.INSTANCE.assemblerMessage(messagePO);
        }
        return null;
    }
}
