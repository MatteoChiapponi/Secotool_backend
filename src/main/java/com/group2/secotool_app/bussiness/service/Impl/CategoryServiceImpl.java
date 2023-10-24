package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.bussiness.service.ICategoryService;
import com.group2.secotool_app.model.entity.Category;
import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        if (categoryRepository.existsByName(category.getName()))
            throw new RuntimeException("category already exists");
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        if (!categoryRepository.existsById(category.getId()))
            throw new RuntimeException("can not update a category doesn't exists");
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void associateProductToCategory(Product product, Category category) {
        category.getProducts().add(product);
        categoryRepository.save(category);
    }

    @Override
    public Category findByName(String categoryName) {
        var category = categoryRepository.findByName(categoryName);
        if (category.isPresent()){
            return category.get();
        }
        throw new RuntimeException("category "+categoryName+" not found");
    }

    @Override
    public Category findById(Long id) {
        var category = categoryRepository.findById(id);
        if (category.isPresent()){
            return category.get();
        }
        throw new RuntimeException("category "+id+" not found");
    }

    @Override
    public List<Category> getAllCategoriesByProduct(Long prodId) {
        return categoryRepository.getAllCategoriesByProduct(prodId);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

}
