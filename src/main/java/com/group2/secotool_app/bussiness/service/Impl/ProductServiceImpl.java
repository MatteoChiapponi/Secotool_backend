package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.model.entity.Image;
import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.persistence.ProductRepository;
import com.group2.secotool_app.bussiness.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getTenRandomProducts() {
        return productRepository.getTenRandomProducts();
    }

    @Override
    public Long save(Product product) {
        var prod = productRepository.save(product);
        return prod.getId();
    }

    @Override
    public void deleteById(Long id, List<Image> images) {
        var prod = new Product();
        prod.setId(id);
        prod.setImages(images);
        productRepository.delete(prod);
    }

    @Override
    public List<Product> paginateProducts(int page) {
        final Pageable pageable = PageRequest.of(page,10);
        return productRepository.findAll(pageable).get().toList();
    }

    @Override
    public Product findProductById(Long id) {
        var product = productRepository.findById(id);
        if (product.isPresent()){
            return product.get();
        }
        throw new RuntimeException("product "+id+ " not found");
    }

    @Override
    public boolean existProductById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public void updateProduct(Product prod) {
        productRepository.deleteAllRelationsAssociatedWithAProductById(prod.getId());
        productRepository.save(prod);
    }

    @Override
    public List<Product> getAllProductsAssociateWithAFeature(Long featureId) {
        return productRepository.findAllByFeatureId(featureId);
    }
    @Override
    public List<Product> getAllProductsAssociateWithACategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Product findByName(String prodName) {
        var product = productRepository.findByName(prodName);
        if (product.isPresent()){
            return product.get();
        }
        throw new RuntimeException("product "+prodName+ " not found");
    }

    @Override
    public void deleteRelationsWithCategoryAndFeatures(Long id) {
        productRepository.deleteAllRelationsAssociatedWithAProductById(id);
    }

    @Override
    public List<Product> getFavoritesProductOfUserById(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    @Override
    public List<Product> getAllProductsByRangeOfDateAvailableToRent(LocalDate startDate, LocalDate endDate) {
        return productRepository.getAllProductsAvaibleToRent(startDate,endDate);
    }

    @Override
    public boolean existProductByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public void updateAverageAndNumberOfScore(Double score, Long productId) {
        var prod = findProductById(productId);

        var oldAverageScore = prod.getAverageScore();
        var oldScores = prod.getNumberOfScores();

        var newScores = oldScores + 1;
        var totalScore = oldAverageScore * oldScores + score;

        var newAverage = totalScore / newScores;

        productRepository.updateAvarageAndNumberOfScore(newAverage,newScores,productId);
    }


}
