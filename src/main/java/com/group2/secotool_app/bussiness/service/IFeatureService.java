package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.entity.Feature;
import com.group2.secotool_app.model.entity.Product;

import java.util.List;

public interface IFeatureService {
    void save(Feature feature);

    void update(Feature feature);

    void delete(Long id);

    List<Feature> findAll();

    void associateProductToFeature(Product product, Feature feature);

    Feature findByName(String featureName);

    Feature findById(Long featureId);

    List<Feature> getAllFeaturesByProduct(Long prodId);

    boolean existsById(Long id);
}
