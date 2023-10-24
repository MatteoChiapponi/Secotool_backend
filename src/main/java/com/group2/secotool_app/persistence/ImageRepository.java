package com.group2.secotool_app.persistence;

import com.group2.secotool_app.model.dto.ImageDto;
import com.group2.secotool_app.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query("SELECT new com.group2.secotool_app.model.dto.ImageDto(i.id, i.url) FROM Image i WHERE i.product.id = :productId")
    List<ImageDto> findAllByProductId(@Param("productId") Long productId);

    @Query("SELECT i FROM Image i JOIN i.category c WHERE c.id = :id")
    Image findByCategoryId(Long id);
}
