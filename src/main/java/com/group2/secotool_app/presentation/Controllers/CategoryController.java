package com.group2.secotool_app.presentation.Controllers;

import com.group2.secotool_app.bussiness.facade.ICategoryFacade;
import com.group2.secotool_app.model.dto.CategoryFullDto;
import com.group2.secotool_app.model.dto.request.CategoryRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/categories")
public class CategoryController {

    private final ICategoryFacade categoryFacade;

    @GetMapping("/open")
    public ResponseEntity<List<CategoryFullDto>> getAllCategories(){
        return ResponseEntity.ok(categoryFacade.getAllCategory());
    }

    @PostMapping("/admin")
    public ResponseEntity<String> saveCategory(@RequestPart("data") @Valid
                                               CategoryRequestDto categoryRequestDto,
                                               @RequestPart("image")
                                               MultipartFile image
    ){
        categoryFacade.saveCategory(categoryRequestDto, image);
        return ResponseEntity.ok("category saved successfully");
    }


    @PutMapping("/admin/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestPart("category-data") @Valid CategoryRequestDto categoryRequestDto, @RequestPart(value = "image", required = false) MultipartFile categoryImage){
        categoryFacade.updateCategory(categoryRequestDto,id, categoryImage);
        return ResponseEntity.ok(String.format("feature %s successfully updated", id));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteFeature(@PathVariable Long id){
        categoryFacade.deleteCategory(id);
        return ResponseEntity.ok(String.format("category %s successfully deleted", id));
    }
}