package com.group2.secotool_app.persistence;

import com.group2.secotool_app.model.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);

    @Query("SELECT p FROM Product p ORDER BY RAND() LIMIT 10")
    List<Product> getTenRandomProducts();

    @Query("SELECT p FROM Product p JOIN p.productFeatures f WHERE f.name = :featureName")
    List<Product> findAllByFeatureName(@Param("featureName") String featureName);

    @Query("SELECT p FROM Product p JOIN p.productCategories c WHERE c.name = :category")
    List<Product> findAllByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p JOIN p.productFeatures f WHERE f.id = :featureId")
    List<Product> findAllByFeatureId(@Param("featureId")Long featureId);

    @Query("SELECT p FROM Product p JOIN p.productCategories c WHERE c.id = :categoryId")
    List<Product> findAllByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p JOIN p.usersFavorite u WHERE u.id = :userId")
    List<Product> findAllByUserId(@Param("userId")Long userId);

    @Query("SELECT p FROM Product p WHERE p.id NOT IN (SELECT r.product.id FROM Rent r WHERE :startDate <= r.rentalEndDate AND :endDate >= r.rentalStartDate)")
    List<Product> getAllProductsAvaibleToRent(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.averageScore = :averageScore, p.numberOfScores = :numberOfScores WHERE p.id = :productId")
    void updateAvarageAndNumberOfScore(@Param("averageScore") Double averageScore, @Param("numberOfScores") Integer numberOfScores, @Param("productId") Long productId);

    @Procedure(name = "deleteAllRelationsAssociatedWithAProductById")
    void deleteAllRelationsAssociatedWithAProductById(@Param("id") Long id);

}
