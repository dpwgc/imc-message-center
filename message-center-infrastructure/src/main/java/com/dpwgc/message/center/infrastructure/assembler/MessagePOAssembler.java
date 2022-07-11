package com.dpwgc.message.center.infrastructure.assembler;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessagePOAssembler {

    MessagePOAssembler INSTANCE = Mappers.getMapper(MessagePOAssembler.class);

    MessagePO assemblerMessagePO(Message message);

    Message assemblerMessage(MessagePO messagePO);
}
