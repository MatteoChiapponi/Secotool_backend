package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.bussiness.service.IProductService;
import com.group2.secotool_app.bussiness.service.IProductValidationService;
import com.group2.secotool_app.model.dto.request.RentProductRequestDto;
import com.group2.secotool_app.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProductValidationServiceImpl implements IProductValidationService {

    private final IProductService productService;

    @Override
    public void validateProductNameIsNotAvailable(String name) {
        if (productService.existProductByName(name))
            throw new RuntimeException("product name already exists on database");
    }

    @Override
    public void isProductAvailableToRent(RentProductRequestDto rentProductRequestDto, Product productToRent) {
        var startDate = rentProductRequestDto.startDate();
        var endDate = rentProductRequestDto.endDate();
        var productsAvailable = productService.getAllProductsByRangeOfDateAvailableToRent(startDate,endDate);
        if (startDate.isBefore(LocalDate.now()))
            throw new RuntimeException("It is not possible to rent a product days before the date");
        if (!productsAvailable.contains(productToRent))
            throw new RuntimeException(
                    String.format("product %s is not available for rent between %s and %s", productToRent.getName(), startDate, endDate)
            );
    }
}
