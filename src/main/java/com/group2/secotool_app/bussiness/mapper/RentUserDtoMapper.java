package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.RentUserDto;
import com.group2.secotool_app.model.entity.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentUserDtoMapper {
    @Mapping(source = "rent.product.id", target = "productId")
    @Mapping(source = "rent.product.name", target = "productName")
    @Mapping(source = "rent.product.images", target = "productImage")
    @Mapping(source = "rent", target = "rentalData")
    RentUserDto toUserRentDto (Rent rent);
}
