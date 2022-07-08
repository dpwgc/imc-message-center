package com.dpwgc.message.center.infrastructure.dal.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MessageMapper extends BaseMapper<MessagePO> {
}
