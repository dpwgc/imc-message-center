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
        queryWrapper.eq("group_id",groupId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<

        queryWrapper.orderByAsc("create_time");    //按时间正序查找聊天消息

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);
        Long count = messageMapper.selectCount(queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));

            //如果是已撤回的消息，则不返回主体信息
            if (messageDTO.getStatus() == 0) {
                messageDTO.setContent("");
            }

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

        queryWrapper.orderByAsc("create_time");    //按时间正序查找聊天消息

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);
        Long count = messageMapper.selectCount(queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));

            //如果是已撤回的消息，则不返回主体信息
            if (messageDTO.getStatus() == 0) {
                messageDTO.setContent("");
            }

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
        queryWrapper.eq("group_id",groupId);
        queryWrapper.eq("user_id",userId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<

        queryWrapper.orderByAsc("create_time");    //按时间正序查找聊天消息

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);
        Long count = messageMapper.selectCount(queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));

            //如果是已撤回的消息，则不返回主体信息
            if (messageDTO.getStatus() == 0) {
                messageDTO.setContent("");
            }

            messageDTOList.add(messageDTO);
        }

        MessagePageDTO messagePageDTO = new MessagePageDTO();
        messagePageDTO.setTotal(count);
        messagePageDTO.setPageMessageList(messageDTOList);

        return messagePageDTO;
    }

    @Override
    public List<MessageDTO> findBeforeByMessageId(String appId, String groupId, String messageId, Integer pageSize) {

        //找到该messageId对应的消息信息
        MessagePO messagePO = findOneMessage(messageId);

        if (messagePO == null) {
            return null;
        }

        //查找在该消息之前的消息列表（pageSize限制返回的消息数量）
        Page<MessagePO> page = new Page<>(0, pageSize);
        QueryWrapper<MessagePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("group_id",groupId);
        queryWrapper.ge("create_time",messagePO.getCreateTime());       //>=

        queryWrapper.orderByAsc("create_time");    //按时间正序查找聊天消息

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            //从消息列表里去除该message本身
            if (messagePOPage.getRecords().get(i).getMessageId().equals(messageId)) {
                continue;
            }
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));

            //如果是已撤回的消息，则不返回主体信息
            if (messageDTO.getStatus() == 0) {
                messageDTO.setContent("");
            }

            messageDTOList.add(messageDTO);
        }

        return messageDTOList;
    }

    @Override
    public List<MessageDTO> findAfterByMessageId(String appId, String groupId, String messageId, Integer pageSize) {

        //找到该messageId对应的消息信息
        MessagePO messagePO = findOneMessage(messageId);

        if (messagePO == null) {
            return null;
        }

        //查找在该消息之后的消息列表（pageSize限制返回的消息数量）
        Page<MessagePO> page = new Page<>(0, pageSize);
        QueryWrapper<MessagePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("group_id",groupId);
        queryWrapper.le("create_time",messagePO.getCreateTime());       //<=

        queryWrapper.orderByAsc("create_time");    //按时间正序查找聊天消息

        Page<MessagePO> messagePOPage = messageMapper.selectPage(page,queryWrapper);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        for (int i=0;i<messagePOPage.getRecords().size();i++) {
            //从消息列表里去除该message本身
            if (messagePOPage.getRecords().get(i).getMessageId().equals(messageId)) {
                continue;
            }
            MessageDTO messageDTO = MessageAssembler.INSTANCE.assembleMessageDTO(messagePOPage.getRecords().get(i));

            //如果是已撤回的消息，则不返回主体信息
            if (messageDTO.getStatus() == 0) {
                messageDTO.setContent("");
            }

            messageDTOList.add(messageDTO);
        }

        return messageDTOList;
    }

    private MessagePO findOneMessage(String messageId) {
        QueryWrapper<MessagePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("message_id",messageId);

        return messageMapper.selectOne(queryWrapper);
    }
}
