package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.model.entity.Rent;
import com.group2.secotool_app.model.entity.User;
import com.group2.secotool_app.model.entity.UserRole;

import java.util.List;

public interface IUserService {
    User findUserById(Long id);
    User findUserByUsername(String username);

    Long saveUser(User user);

    List<User> findAllUser();

    void changeUserRole(Long userId, UserRole userRole);

    void addProductToFavorite(Long userId,Product product, List<Product> favoritesProducts);

    void removeProductToFavorite(Long userId, Product product, List<Product> favoritesProducts);

    boolean existsByUsername(String username);

    void updateUserDni(String dni, Long userId);
}
