package com.dpwgc.message.center.app.command.chat.assembler;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageAssembler {

    MessageDTO assembleMessageDTO(Message message);
}
