package com.dpwgc.message.center.app.assembler;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageAssembler {

    MessageAssembler INSTANCE = Mappers.getMapper(MessageAssembler.class);

    MessageDTO assembleMessageDTO(Message message);

    MessageDTO assembleMessageDTO(MessagePO messagePO);
}
