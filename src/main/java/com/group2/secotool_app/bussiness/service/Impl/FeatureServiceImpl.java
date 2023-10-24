package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.bussiness.service.IFeatureService;
import com.group2.secotool_app.model.entity.Feature;
import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.persistence.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements IFeatureService {
    private final FeatureRepository featureRepository;
    @Override
    public void save(Feature feature) {
        if (featureRepository.existsByName(feature.getName()))
            throw new RuntimeException("feature already exists");
        featureRepository.save(feature);
    }

    @Override
    public void update(Feature feature) {
        if (!featureRepository.existsById(feature.getId()))
            throw new RuntimeException("can not update a feature doesn't exists");
        featureRepository.save(feature);

    }

    @Override
    public void delete(Long id) {
        featureRepository.deleteById(id);
    }

    @Override
    public List<Feature> findAll() {
        return featureRepository.findAll();
    }

    @Override
    public void associateProductToFeature(Product product, Feature feature) {
        feature.getProducts().add(product);
        featureRepository.save(feature);
    }

    @Override
    public Feature findByName(String featureName) {
        var feature = featureRepository.findByName(featureName);
        if (feature.isPresent()){
            return feature.get();
        }
        throw new RuntimeException("feature "+featureName+ " not found");
    }

    @Override
    public Feature findById(Long featureId) {
        var feature = featureRepository.findById(featureId);
        if (feature.isPresent()){
            return feature.get();
        }
        throw new RuntimeException("feature "+featureId+ " not found");
    }

    @Override
    public List<Feature> getAllFeaturesByProduct(Long prodId) {
        return featureRepository.getAllFeaturesByProduct(prodId);
    }

    @Override
    public boolean existsById(Long id) {
        return featureRepository.existsById(id);
    }

}
