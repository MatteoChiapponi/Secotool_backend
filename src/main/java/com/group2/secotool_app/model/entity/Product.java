package com.group2.secotool_app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "double(2,1) default '0'")
    private Double averageScore = 0.0;

    private Integer numberOfScores = 0;

    @OneToMany(mappedBy = "product", cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JsonIgnore
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    private List<Rent> productRentals;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    @JsonIgnore
    private List<Feature> productFeatures;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "products")
    @JsonIgnore
    private List<Category> productCategories;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favoritesProducts")
    @JsonIgnore
    private List<User> usersFavorite;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    private List<Review> productReviews;
}