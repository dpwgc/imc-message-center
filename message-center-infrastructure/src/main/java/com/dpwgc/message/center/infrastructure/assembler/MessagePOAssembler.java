package com.dpwgc.message.center.infrastructure.assembler;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessagePOAssembler {

    MessagePO assemblerMessagePO(Message message);
}
