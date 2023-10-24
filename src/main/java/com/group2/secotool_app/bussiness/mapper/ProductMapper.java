package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.request.ProductRequestDto;
import com.group2.secotool_app.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    Product toProduct (ProductRequestDto product);
}
