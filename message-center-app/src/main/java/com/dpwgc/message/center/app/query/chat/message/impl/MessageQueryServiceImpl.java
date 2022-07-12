package com.dpwgc.message.center.app.query.chat.message.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.message.center.app.assembler.MessageAssembler;
import com.dpwgc.message.center.app.query.chat.message.MessageQueryService;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.infrastructure.dal.chat.mapper.MessageMapper;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import com.dpwgc.message.center.sdk.model.chat.message.MessagePageDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    @Resource
    MessageMapper messageMapper;

    @Override
    public MessagePageDTO findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<MessagePO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MessagePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("group_id",appId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);
        Long count = messageMapper.selectCount(queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));
            messageDTOList.add(messageDTO);
        }

        MessagePageDTO messagePageDTO = new MessagePageDTO();
        messagePageDTO.setTotal(count);
        messagePageDTO.setPageMessageList(messageDTOList);

        return messagePageDTO;
    }
    @Override
    public MessagePageDTO findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<MessagePO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MessagePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("user_id",userId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);
        Long count = messageMapper.selectCount(queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));
            messageDTOList.add(messageDTO);
        }

        MessagePageDTO messagePageDTO = new MessagePageDTO();
        messagePageDTO.setTotal(count);
        messagePageDTO.setPageMessageList(messageDTOList);

        return messagePageDTO;
    }
    @Override
    public MessagePageDTO findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<MessagePO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MessagePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("group_id",appId);
        queryWrapper.eq("user_id",userId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);
        Long count = messageMapper.selectCount(queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));
            messageDTOList.add(messageDTO);
        }

        MessagePageDTO messagePageDTO = new MessagePageDTO();
        messagePageDTO.setTotal(count);
        messagePageDTO.setPageMessageList(messageDTOList);

        return messagePageDTO;
    }
}
