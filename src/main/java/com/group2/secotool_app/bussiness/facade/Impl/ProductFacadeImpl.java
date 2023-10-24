package com.group2.secotool_app.bussiness.facade.Impl;

import com.group2.secotool_app.bussiness.facade.ICategoryFacade;
import com.group2.secotool_app.bussiness.facade.IFeatureFacade;
import com.group2.secotool_app.bussiness.facade.IProductFacade;
import com.group2.secotool_app.bussiness.mapper.*;
import com.group2.secotool_app.bussiness.service.*;
import com.group2.secotool_app.model.dto.*;
import com.group2.secotool_app.model.dto.request.IdListRequestDto;
import com.group2.secotool_app.model.dto.request.ProductRequestDto;
import com.group2.secotool_app.util.CommonUtils;
import com.group2.secotool_app.util.ProductUtils;
import com.group2.secotool_app.util.RentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFacadeImpl implements IProductFacade {

    private final IFeatureFacade featureFacade;
    private final ICategoryFacade categoryFacade;
    private final IProductService productService;
    private final IProductValidationService productValidationService;
    private final IImageService imageService;
    private final IFileService fileService;
    private final IBucketS3Service bucketS3Service;
    private final ProductMapper productMapper;
    private final ProductUtils productUtils;
    private final RentUtils rentUtils;
    private final CommonUtils commonUtils;

    @Override
    public List<ProductDto> getAllProducts() {
        var prods = productService.getAllProducts();
        return productUtils.productsToProductsDto(prods);
    }

    @Override
    public List<ProductDto> getTenRandomProducts() {
        var randomProds = productService.getTenRandomProducts();
        return productUtils.productsToProductsDto(randomProds);
    }

    //se puede refactorizar hacer todas las sentencias por list
    @Override
    public void updateProduct(Long id, ProductRequestDto productRequestDto, IdListRequestDto idCategories, IdListRequestDto idFeatures, IdListRequestDto idImages, List<MultipartFile> images) {

        var oldProduct = productService.findProductById(id);

        var newProduct = productMapper.toProduct(productRequestDto);

        newProduct.setId(id);
        newProduct.setAverageScore(oldProduct.getAverageScore());
        newProduct.setNumberOfScores(oldProduct.getNumberOfScores());
        newProduct.setProductRentals(oldProduct.getProductRentals());
        newProduct.setProductReviews(oldProduct.getProductReviews());

        var proOldImages = imageService.getAllImagesByProduct(id);

        idImages.idsList().forEach(imageId -> {
            proOldImages.forEach(image -> {
                if (imageId.equals(image.getId()))
                    imageService.deleteImage(image.getId());
            });
        });

        if (images != null){
            fileService.validateFilesAreImages(images);
            var urlImages = bucketS3Service.storeFiles(images);
            urlImages.forEach(url -> {
                imageService.saveProductImage(url,id);
            });
        }

        productService.updateProduct(newProduct);

        idFeatures.idsList().forEach(featureId ->
                featureFacade.associateProductToFeature(newProduct,featureId));
        idCategories.idsList().forEach(categoryId ->
                categoryFacade.associateProductToCategory(newProduct,categoryId)
        );
    }
    //se puede refactorizar
    @Override
    public String save(ProductRequestDto productRequestDto, IdListRequestDto categoriesId, IdListRequestDto featuresId, List<MultipartFile> images) {
        var productName = productRequestDto.name();
        productValidationService.validateProductNameIsNotAvailable(productName);
        fileService.validateFilesAreImages(images);

        var product = productMapper.toProduct(productRequestDto);

        Long prodId = productService.save(product);
        product.setId(prodId);

        featuresId.idsList().forEach(id ->
                featureFacade.associateProductToFeature(product,id)
        );

        categoriesId.idsList().forEach(id ->
                categoryFacade.associateProductToCategory(product,id)
        );

        var urlImages = bucketS3Service.storeFiles(images);

        urlImages.forEach(url ->
                imageService.saveProductImage(url,prodId)
        );
        return "product saved successfully";
    }

    @Override
    public String deleteById(Long id) {
        var images = imageService.getAllImagesByProduct(id);
        productService.deleteRelationsWithCategoryAndFeatures(id);
        productService.deleteById(id,images);
        return "product "+id+ " successfully deleted";
    }

    @Override
    public List<ProductDto> paginateProducts(int page) {
        var products = productService.paginateProducts(page);
        return productUtils.productsToProductsDto(products);
    }

    @Override
    public ProductFullDto findProductById(Long id) {
        var product = productService.findProductById(id);
        return productUtils.productToProductFullDto(product);
    }

    @Override
    public List<ProductDto> getAllProductsAssociateWithAFeature(Long featureId) {
        var prods = productService.getAllProductsAssociateWithAFeature(featureId);
        return productUtils.productsToProductsDto(prods);
    }

    @Override
    public List<RentProductDto> getAllProductsByRangeOfDateAvailableToRent(LocalDate startDate, LocalDate endDate, String productName) {

        var totalDays = productUtils.daysQuantity(startDate,endDate);
        List<RentProductDto> response = new ArrayList<>();
        var prodsAvailable = productService.getAllProductsByRangeOfDateAvailableToRent(startDate,endDate);
        var prodsAvailableDto = productUtils.productsToProductsDto(prodsAvailable);

        if (productName == null || productName.equals("")) {
            prodsAvailableDto.forEach(productDto -> {
                var totalPrice = rentUtils.calculateTotalPriceOfRent(totalDays,productDto.price());
                response.add(new RentProductDto(startDate,endDate,totalDays,totalPrice,productDto));
            });
        }else {
            var userProductName = commonUtils.normalizeText(productName);
            prodsAvailableDto.forEach(productDto -> {
                var prodName = commonUtils.normalizeText(productDto.name());
                if (prodName.contains(userProductName)){
                    var totalPrice = rentUtils.calculateTotalPriceOfRent(totalDays,productDto.price());
                    response.add(new RentProductDto(startDate,endDate,totalDays,totalPrice,productDto));
                }
            });
        }
        return response;
    }

    @Override
    public List<ProductDto> getAllProductsAssociateWithACategory(IdListRequestDto categoriesId) {

        List<List<ProductDto>> productDtosMatriz = new ArrayList<>();
        List<ProductDto> productDtoList = new ArrayList<>();

        categoriesId.idsList().forEach(categoryId -> {
            var prods = productService.getAllProductsAssociateWithACategory(categoryId);
            productDtosMatriz.add(productUtils.productsToProductsDto(prods));
        });

        //Iteration can be replaced with bulk 'Collection.addAll()' call
        productDtosMatriz.forEach(arrayProd ->
                arrayProd.forEach(product -> productDtoList.add(product))
        );

        return productUtils.removeDuplicated(productDtoList);

    }



}
