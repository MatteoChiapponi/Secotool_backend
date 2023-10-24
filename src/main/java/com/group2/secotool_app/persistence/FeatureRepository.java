package com.group2.secotool_app.persistence;

import com.group2.secotool_app.model.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Long> {

    boolean existsByName(String name);

    Optional<Feature> findByName(String name);

    @Query("SELECT f FROM Feature f JOIN f.products p WHERE p.id = :prodId")
    List<Feature> getAllFeaturesByProduct(Long prodId);

}
