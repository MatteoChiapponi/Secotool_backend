package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.ImageDto;
import com.group2.secotool_app.model.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "url", target = "url")
    Image toImage(ImageDto imageDto);
}
