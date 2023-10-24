package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.dto.request.RentProductRequestDto;
import com.group2.secotool_app.model.entity.Product;

public interface IProductValidationService {
    void validateProductNameIsNotAvailable(String name);
    void isProductAvailableToRent(RentProductRequestDto rentProductRequestDto, Product productToRent);
}
