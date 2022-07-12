package com.dpwgc.message.center.app.assembler;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageAssembler {

    MessageDTO assembleMessageDTO(Message message);

    MessageDTO assembleMessageDTO(MessagePO messagePO);
}
