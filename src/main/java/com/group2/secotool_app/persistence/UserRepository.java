package com.group2.secotool_app.persistence;

import com.group2.secotool_app.model.entity.User;
import com.group2.secotool_app.model.entity.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User SET userRole = :role WHERE id = :userId")
    void updateUserRole(@Param("userId") Long id, @Param("role") UserRole userRole);

    @Transactional
    @Modifying
    @Query("UPDATE User SET dni = :dni WHERE id = :userId")
    void updateUserDni(@Param("dni") String dni, @Param("userId") Long userId);
}
