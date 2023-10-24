package com.group2.secotool_app.bussiness.facade.Impl;

import com.group2.secotool_app.bussiness.facade.ICategoryFacade;
import com.group2.secotool_app.bussiness.mapper.CategoryFullDtoMapper;
import com.group2.secotool_app.bussiness.mapper.CategoryMapper;
import com.group2.secotool_app.bussiness.service.*;
import com.group2.secotool_app.model.dto.CategoryFullDto;
import com.group2.secotool_app.model.dto.request.CategoryRequestDto;
import com.group2.secotool_app.model.entity.Category;
import com.group2.secotool_app.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryFacadeImpl implements ICategoryFacade {
    private final ICategoryService categoryService;
    private final IImageService imageService;
    private final IFileService fileService;
    private final CategoryMapper categoryMapper;
    private final CategoryFullDtoMapper categoryFullDtoMapper;
    private final IBucketS3Service bucketS3Service;
    @Override
    public List<CategoryFullDto> getAllCategory() {
        List<CategoryFullDto> categoryFullDtos = new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        categories.forEach(category -> {
            category.setImage(imageService.getImageByCategoryId(category.getId()));
            categoryFullDtos.add(categoryFullDtoMapper.toCategoryFullDto(category));
        });
        return categoryFullDtos;
    }

    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto, MultipartFile image) {
        var imageArray = Arrays.asList(image);
        fileService.validateFilesAreImages(imageArray);
        var urlImage = bucketS3Service.storeFiles(imageArray);
        System.out.println("se guardarn las imagenes");
        Category category = categoryMapper.toCategory(categoryRequestDto);
        urlImage.forEach(url ->
                category.setImage(imageService.saveCategoryImage(url))
        );
        categoryService.save(category);
    }

    @Override
    public void updateCategory(CategoryRequestDto categoryRequestDto, Long id, MultipartFile categoryImage) {
        var oldCategory = categoryService.findById(id);
        var newCategory = categoryMapper.toCategory(categoryRequestDto);
        newCategory.setId(id);
        newCategory.setProducts(oldCategory.getProducts());

        if (categoryImage != null){
            var imageArray = Arrays.asList(categoryImage);
            imageService.deleteImage(oldCategory.getImage().getId());
            fileService.validateFilesAreImages(imageArray);
            var urlImages = bucketS3Service.storeFiles(imageArray);
            urlImages.forEach(url -> {
               var image =  imageService.saveCategoryImage(url);
               newCategory.setImage(image);
            });
        }else
            newCategory.setImage(oldCategory.getImage());

        categoryService.update(newCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryService.delete(id);
    }

    //buscar todas las category aasociadas
    @Override
    public void associateProductToCategory(Product product, Long categoryId) {
        product.setProductCategories(categoryService.getAllCategoriesByProduct(product.getId()));
        var category = categoryService.findById(categoryId);
        categoryService.associateProductToCategory(product,category);
    }
}
