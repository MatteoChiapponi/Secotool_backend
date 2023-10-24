package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.CategoryFullDto;
import com.group2.secotool_app.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryFullDtoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    CategoryFullDto toCategoryFullDto(Category category);
}
