package com.group2.secotool_app.util;

import com.group2.secotool_app.bussiness.mapper.ProductDtoMapper;
import com.group2.secotool_app.bussiness.mapper.ProductFullDtoMapper;
import com.group2.secotool_app.bussiness.service.IUserValidationService;
import com.group2.secotool_app.model.dto.ProductDto;
import com.group2.secotool_app.model.dto.ProductFullDto;
import com.group2.secotool_app.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
@RequiredArgsConstructor
public class ProductUtils {
    private final ProductDtoMapper productDtoMapper;
    private final ProductFullDtoMapper productFullDtoMapper;
    private final IUserValidationService userValidationService;

    public List<ProductDto> removeDuplicated(List<ProductDto> productDtos){
        List<ProductDto> productDtoList = new ArrayList<>(productDtos);
        productDtoList.sort((p1,p2) -> p1.compareTo(p2));

        for (int i = 0; i < productDtoList.size() - 1; i++) {
            if (productDtoList.get(i).id().equals(productDtoList.get(i+1).id())){
                productDtoList.remove(i+1);
            }
        }
        return productDtoList;
    }

    public long daysQuantity (LocalDate startDate, LocalDate endDate){
        return DAYS.between(startDate, endDate);
    }

    public List<ProductDto> productsToProductsDto(List<Product> products){

        ArrayList<ProductDto> productsDto = new ArrayList<>();
        var userOptional = userValidationService.isUserAuthenticated();
        if (userOptional.isPresent()){
            var user = userOptional.get();
            var favProducts = user.getFavoritesProducts();
            products.forEach(product -> {
                favProducts.forEach(fav -> {
                    if (product.getId().equals(fav.getId())){
                        productsDto.add(productDtoMapper.toProductFavoritetDto(product,true));
                    }
                });
                productsDto.add(productDtoMapper.toProductDto(product));
            });
            return removeDuplicated(productsDto);
        }
        else
            products.forEach(product -> productsDto.add(productDtoMapper.toProductDto(product)));
        return productsDto;
    }

    public ProductFullDto productToProductFullDto (Product product){
        Boolean isFav = false;
        var userOptional = userValidationService.isUserAuthenticated();
        if (userOptional.isPresent()){
            var user = userOptional.get();
            var favProducts = user.getFavoritesProducts();
            for (Product favProd:favProducts) {
                if (favProd.getId().equals(product.getId()))
                    isFav=true;
            }
        }
        System.out.println(product.getProductReviews().size());
        return productFullDtoMapper.toFavoriteProductFullDto(product,isFav);
    }


}
