package com.group2.secotool_app.presentation.Controllers;

import com.group2.secotool_app.bussiness.facade.IFeatureFacade;
import com.group2.secotool_app.model.dto.FeatureDto;
import com.group2.secotool_app.model.dto.request.FeatureRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/features")
public class FeatureController {

    private final IFeatureFacade featureFacade;

    @GetMapping("/open")
    public ResponseEntity<List<FeatureDto>> getAllFeatures(){
        return ResponseEntity.ok(featureFacade.getAllFeatures());
    }

    @PostMapping("/admin")
    public ResponseEntity<String> saveFeature(@RequestBody @Valid FeatureRequestDto featureRequestDto){
        featureFacade.saveFeature(featureRequestDto);
        return ResponseEntity.ok("feature saved successfully");
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<String> updateFeature(@RequestBody @Valid FeatureRequestDto featureRequestDto, @PathVariable Long id){
        featureFacade.updateFeature(featureRequestDto,id);
        return ResponseEntity.ok(String.format("feature %s successfully updated", id));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteFeature(@PathVariable Long id){
        featureFacade.deleteFeature(id);
        return ResponseEntity.ok(String.format("feature %s successfully deleted", id));
    }

}