package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.bussiness.service.IUserService;
import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.model.entity.User;
import com.group2.secotool_app.model.entity.UserRole;
import com.group2.secotool_app.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            return userOptional.get();
        }
        throw new RuntimeException("user "+id+ " not found ");
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent())
            return user.get();
        else
            throw new RuntimeException("user "+username+ " not found ");
    }

    @Override
    public Long saveUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user).getId();
        }
        throw new RuntimeException("username not available");
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void changeUserRole(Long userId, UserRole userRole) {
        userRepository.updateUserRole(userId,userRole);
    }

    @Override
    public void addProductToFavorite(Long userId, Product product, List<Product> favoritesProducts) {
        if (favoritesProducts.contains(product))
            throw new RuntimeException("product is already in favorites");
        var user = findUserById(userId);
        favoritesProducts.add(product);
        user.setFavoritesProducts(favoritesProducts);
        userRepository.save(user);
    }

    @Override
    public void removeProductToFavorite(Long userId, Product product, List<Product> favoritesProducts) {
        if (!favoritesProducts.remove(product))
            throw new RuntimeException("product is not in your favorites list");
        var user = findUserById(userId);
        user.setFavoritesProducts(favoritesProducts);
        userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void updateUserDni(String dni, Long userId) {
        userRepository.updateUserDni(dni,userId);
    }
}
