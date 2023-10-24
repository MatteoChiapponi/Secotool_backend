package com.group2.secotool_app.presentation.Controllers;

import com.group2.secotool_app.bussiness.facade.IProductFacade;
import com.group2.secotool_app.model.dto.ProductDto;
import com.group2.secotool_app.model.dto.ProductFullDto;
import com.group2.secotool_app.model.dto.RentProductDto;
import com.group2.secotool_app.model.dto.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductFacade productFacade;


    @GetMapping("/open")
    @Operation(summary = "return a list of all products saved in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "list of products")
    })
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productFacade.getAllProducts());
    }

    @GetMapping("/open/{id}")
    @Operation(summary = "return a product saved on database by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "product found"),
            @ApiResponse(responseCode = "400",description = "product not found")
    })
    public ResponseEntity<ProductFullDto> getProductById(@Parameter(description = "id of product ") @PathVariable Long id){
        return ResponseEntity.ok(productFacade.findProductById(id));
    }

    @Operation(summary = "returns a list of all products available to be rented by date range and optionally by product name")
    @GetMapping("/open/rentals")
    public ResponseEntity<List<RentProductDto>> getAllProductsByRangeOfDateAvailableToRent(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate , @RequestParam(value = "productName",required = false) String productName){
        return ResponseEntity.ok(productFacade.getAllProductsByRangeOfDateAvailableToRent(startDate,endDate,productName));
    }

    @GetMapping("/open/random")
    @Operation(summary = "returns ten random products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "list of random products")
    })
    public ResponseEntity<List<ProductDto>> getTenRandomProducts(){
        return ResponseEntity.ok(productFacade.getTenRandomProducts());
    }

    @GetMapping("/open/paginate/{page}")
    @Operation(summary = "returns a list of ten products according to the specified index. 0 = first 10 products 1 = second 10 products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "page of required products")
    })
    public ResponseEntity<List<ProductDto>> productPaginationTenByTen(@Parameter(description = "index of products needed")@PathVariable int page){
        return ResponseEntity.ok(productFacade.paginateProducts(page));
    }


    @GetMapping("/open/feature/{featureId}")
    @Operation(summary = "returns a list of products that are associated with the feature id that is passed by url")
    public ResponseEntity<List<ProductDto>> getAllProductsAssociateWithAFeature(@PathVariable Long featureId){
        return ResponseEntity.ok(productFacade.getAllProductsAssociateWithAFeature(featureId));
    }

    @GetMapping("/open/category")
    @Operation(summary = "returns a list of products that are associated with the ids of features that are passed through the url parameters")
    public ResponseEntity<List<ProductDto>> filterProductsByCategories(@RequestParam("idCategory") List<Long> categoriesId){
        IdListRequestDto idCategories = new IdListRequestDto(categoriesId);
        return ResponseEntity.ok(productFacade.getAllProductsAssociateWithACategory(idCategories));
    }

    @PostMapping("/admin")
    @Operation(summary = "save a product on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "product saved successfully"),
            @ApiResponse(responseCode = "400",description = "invalid body fields"),
            @ApiResponse(responseCode = "406",description = "product name already exists on database")
    })
    public ResponseEntity<String> saveProduct(@Parameter(description = "")
                                              @RequestPart("product-data") @Valid
                                              ProductRequestDto productRequestDto,
                                              @RequestPart("id-categories") @Valid
                                                  IdListRequestDto categoriesId,
                                              @RequestPart("id-features") @Valid
                                                  IdListRequestDto featuresId,
                                              @RequestParam("images")
                                              @NotNull(message = "images requerid")
                                              @NotEmpty(message = "list can not be empy")
                                              @Valid
                                              List<MultipartFile> images ){
        return ResponseEntity.status(201).body(productFacade.save(productRequestDto, categoriesId, featuresId,images));
    }

    @PutMapping("/admin/{id}")
    @Operation(summary = "update a product saved on database")
    public ResponseEntity<?> updateProduct(@PathVariable Long id ,
                                           @RequestPart("product-data") @Valid
                                               ProductRequestDto productRequestDto,
                                           @RequestPart("id-categories") @Valid
                                               IdListRequestDto idCategories,
                                           @RequestPart("id-features") @Valid
                                               IdListRequestDto idFeatures,
                                           @RequestPart("id-images-delete") @Valid
                                               IdListRequestDto idImages,
                                           @RequestPart(value = "images", required = false)
                                           List<MultipartFile> images){
        productFacade.updateProduct(id, productRequestDto, idCategories, idFeatures, idImages,images);
        return ResponseEntity.ok(String.format("product %s succesffully updated",id));
    }


    // cuando se elimine un prd hay que borrar las imagenes de la db y del buvket s3 de aws
    @DeleteMapping("/admin/{id}")
    @Operation(summary = "delete a product saved on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "product successfully deleted")
    })

    public ResponseEntity<String> deleteProductById(@Parameter(description = "product id to remove") @PathVariable @Positive @Valid Long id){
        return ResponseEntity.ok(productFacade.deleteById(id));
    }

}
