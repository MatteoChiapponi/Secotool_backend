package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.entity.Image;
import com.group2.secotool_app.model.entity.Product;

import java.time.LocalDate;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    List<Product> getTenRandomProducts();
    List<Product> getAllProductsAssociateWithACategory(Long categoryId);

    Long save(Product productRequestDto);

    void deleteById(Long id, List<Image> images);

    List<Product> paginateProducts(int page);

    Product findProductById(Long id);
    boolean existProductById(Long id);

    void updateProduct(Product prod);

    List<Product> getAllProductsAssociateWithAFeature(Long featureId);

    Product findByName(String prodName);

    void deleteRelationsWithCategoryAndFeatures(Long id);

    List<Product> getFavoritesProductOfUserById(Long userId);

    List<Product> getAllProductsByRangeOfDateAvailableToRent(LocalDate startDate, LocalDate endDate);

    boolean existProductByName(String name);

    void updateAverageAndNumberOfScore(Double score, Long productId);

}
