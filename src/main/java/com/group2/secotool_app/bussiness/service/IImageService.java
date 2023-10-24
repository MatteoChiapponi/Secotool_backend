package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.entity.Image;

import java.util.List;

public interface IImageService {
    void saveProductImage(String imageUrl, Long prodId);
    Image saveCategoryImage(String imageUrl);
    List<Image> getAllImagesByProduct(Long productId);
    void deleteImage(Long imageId);

    Image getImageByCategoryId(Long id);
}
