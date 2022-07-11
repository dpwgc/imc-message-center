package com.dpwgc.message.center.infrastructure.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InformationPOAssembler {

    InformationPOAssembler INSTANCE = Mappers.getMapper(InformationPOAssembler.class);

}
