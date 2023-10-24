package com.group2.secotool_app.bussiness.facade;

import com.group2.secotool_app.model.dto.CategoryFullDto;
import com.group2.secotool_app.model.dto.request.CategoryRequestDto;
import com.group2.secotool_app.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryFacade {
    List<CategoryFullDto> getAllCategory();

    void saveCategory(CategoryRequestDto categoryRequestDto, MultipartFile image);

    void updateCategory(CategoryRequestDto categoryRequestDto, Long id, MultipartFile categoryImage);

    void deleteCategory(Long id);

    void associateProductToCategory(Product product, Long categoryId);
}
