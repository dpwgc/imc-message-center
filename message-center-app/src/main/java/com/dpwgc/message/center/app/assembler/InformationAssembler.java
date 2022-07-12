package com.dpwgc.message.center.app.assembler;

import com.dpwgc.message.center.domain.notice.information.Information;
import com.dpwgc.message.center.infrastructure.dal.notice.entity.InformationPO;
import com.dpwgc.message.center.sdk.model.notice.information.InformationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InformationAssembler {

    InformationAssembler INSTANCE = Mappers.getMapper(InformationAssembler.class);

    InformationDTO assembleInformationDTO(Information information);

    InformationDTO assembleInformationDTO(InformationPO informationPO);
}
