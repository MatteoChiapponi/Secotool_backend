package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.request.PoliticRequestDto;
import com.group2.secotool_app.model.entity.Politic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PoliticMapper {

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    Politic toPolitic(PoliticRequestDto politicRequestDto);

}
