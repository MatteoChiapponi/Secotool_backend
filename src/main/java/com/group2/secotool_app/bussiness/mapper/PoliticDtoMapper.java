package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.PoliticDto;
import com.group2.secotool_app.model.entity.Politic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PoliticDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    PoliticDto toPoliticDto(Politic politic);
}
