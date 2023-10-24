package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.entity.Category;

import com.group2.secotool_app.model.entity.Product;

import java.util.List;

public interface ICategoryService {
    void save(Category category);

    void update(Category category);

    void delete(Long id);

    List<Category> findAll();

    void associateProductToCategory(Product product, Category category);

    Category findByName(String categoryName);

    Category findById(Long id);

    List<Category> getAllCategoriesByProduct(Long prodId);

    boolean existsById(Long id);
}
