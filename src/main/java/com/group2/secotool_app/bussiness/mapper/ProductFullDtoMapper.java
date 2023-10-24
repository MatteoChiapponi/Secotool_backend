package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.ProductFullDto;
import com.group2.secotool_app.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductFullDtoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "averageScore", target = "averageScore")
    @Mapping(source = "numberOfScores", target = "numberOfScores")
    @Mapping(source = "images", target = "images")
    @Mapping(source = "productFeatures", target = "productFeatures")
    @Mapping(source = "productCategories", target = "productCategories")
    @Mapping(source = "productRentals", target = "productRentals")
    @Mapping(source = "productReviews", target = "productReviews")
    ProductFullDto toProductFullDto(Product product);


    @Mapping(source = "product.id", target = "id")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.description", target = "description")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "product.averageScore", target = "averageScore")
    @Mapping(source = "product.numberOfScores", target = "numberOfScores")
    @Mapping(source = "isFavorite", target = "isFavorite")
    @Mapping(source = "product.images", target = "images")
    @Mapping(source = "product.productFeatures", target = "productFeatures")
    @Mapping(source = "product.productCategories", target = "productCategories")
    @Mapping(source = "product.productRentals", target = "productRentals")
    @Mapping(source = "product.productReviews", target = "productReviews")
    ProductFullDto toFavoriteProductFullDto (Product product, Boolean isFavorite);
}
