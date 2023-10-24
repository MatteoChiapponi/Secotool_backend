package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.CategoryDto;
import com.group2.secotool_app.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    CategoryDto toCategoryDto(Category category);

}
