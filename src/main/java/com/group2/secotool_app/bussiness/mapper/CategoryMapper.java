package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.request.CategoryRequestDto;
import com.group2.secotool_app.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category toCategory(CategoryRequestDto categoryRequestDto);
}
