package com.dpwgc.message.center.infrastructure.assembler;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.notice.information.Information;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.infrastructure.dal.notice.entity.InformationPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InformationPOAssembler {

    InformationPOAssembler INSTANCE = Mappers.getMapper(InformationPOAssembler.class);

    InformationPO assemblerInformationPO(Information information);

    Information assemblerInformation(InformationPO informationPO);

}
