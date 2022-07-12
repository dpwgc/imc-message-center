package com.dpwgc.message.center.app.query.chat.message.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.message.center.app.command.chat.assembler.MessageAssembler;
import com.dpwgc.message.center.app.query.chat.message.MessageQueryService;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.infrastructure.assembler.MessagePOAssembler;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.infrastructure.dal.chat.mapper.MessageMapper;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    @Resource
    MessageMapper messageMapper;

    @Resource
    MessageAssembler messageAssembler;

    @Override
    public List<MessageDTO> findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<MessagePO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MessagePO> queryOrder = new QueryWrapper<>();

        queryOrder.eq("app_id",appId);
        queryOrder.eq("group_id",appId);
        queryOrder.ge("create_time",startTime);  //>=
        queryOrder.le("create_time",endTime);      //<=

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryOrder);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(messagePOPage.getRecords().get(i));
            messageDTOList.add(messageDTO);
        }

        return messageDTOList;
    }
    @Override
    public List<MessageDTO> findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<MessagePO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MessagePO> queryOrder = new QueryWrapper<>();

        queryOrder.eq("app_id",appId);
        queryOrder.eq("user_id",userId);
        queryOrder.ge("create_time",startTime);  //>=
        queryOrder.le("create_time",endTime);      //<=

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryOrder);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(messagePOPage.getRecords().get(i));
            messageDTOList.add(messageDTO);
        }

        return messageDTOList;
    }
    @Override
    public List<MessageDTO> findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<MessagePO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MessagePO> queryOrder = new QueryWrapper<>();

        queryOrder.eq("app_id",appId);
        queryOrder.eq("group_id",appId);
        queryOrder.eq("user_id",userId);
        queryOrder.ge("create_time",startTime);  //>=
        queryOrder.le("create_time",endTime);      //<=

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryOrder);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = messageAssembler.assembleMessageDTO(messagePOPage.getRecords().get(i));
            messageDTOList.add(messageDTO);
        }

        return messageDTOList;
    }
}
