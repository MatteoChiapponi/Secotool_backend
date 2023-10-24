package com.group2.secotool_app.bussiness.facade;


import com.group2.secotool_app.model.dto.ProductDto;
import com.group2.secotool_app.model.dto.ProductFullDto;
import com.group2.secotool_app.model.dto.RentProductDto;
import com.group2.secotool_app.model.dto.request.IdListRequestDto;
import com.group2.secotool_app.model.dto.request.ProductRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface IProductFacade {
    List<ProductDto> getAllProducts();
    List<ProductDto> getTenRandomProducts();
    List<ProductDto> getAllProductsAssociateWithACategory(IdListRequestDto categoriesId);

    @Transactional(rollbackOn = {RuntimeException.class})
    String save(ProductRequestDto productRequestDto, IdListRequestDto categoriesId, IdListRequestDto featuresId, List<MultipartFile> images);

    String deleteById(Long id);

    List<ProductDto> paginateProducts(int page);

    ProductFullDto findProductById(Long id);

    void updateProduct(Long id, ProductRequestDto productRequestDto, IdListRequestDto idCategories, IdListRequestDto idFeatures, IdListRequestDto idImages,  List<MultipartFile> images);

    List<ProductDto> getAllProductsAssociateWithAFeature(Long featureId);

    List<RentProductDto> getAllProductsByRangeOfDateAvailableToRent(LocalDate startDate, LocalDate endDate, String productName);
}
