package com.dpwgc.message.center.app.assembler;

import com.dpwgc.message.center.domain.notice.information.Information;
import com.dpwgc.message.center.infrastructure.dal.notice.entity.InformationPO;
import com.dpwgc.message.center.sdk.model.notice.information.InformationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InformationAssembler {

    InformationDTO assembleInformationDTO(Information information);

    InformationDTO assembleInformationDTO(InformationPO informationPO);
}
