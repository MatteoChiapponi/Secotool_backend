package com.group2.secotool_app.bussiness.facade;

import com.group2.secotool_app.model.dto.ProductDto;
import com.group2.secotool_app.model.dto.UserAuthenticatedResponseDto;
import com.group2.secotool_app.model.dto.UserDto;
import com.group2.secotool_app.model.dto.UserGetMeDto;
import com.group2.secotool_app.model.dto.request.ResendRegistrationEmailRequestDto;
import com.group2.secotool_app.model.dto.request.UserAuthenticationRequestDto;
import com.group2.secotool_app.model.dto.request.UserRegistrationRequestDto;
import com.group2.secotool_app.model.entity.UserRole;
import jakarta.mail.MessagingException;

import java.util.List;

public interface IUserFacade {
    UserGetMeDto findUserById(Long id);
    UserAuthenticatedResponseDto authenticateUser(UserAuthenticationRequestDto authenticationRequestDto);
    void registerUser(UserRegistrationRequestDto registerRequestDto) throws MessagingException;
    List<UserDto> getAllUsers();
    void changeUserRole(Long userId, UserRole userRole);

    void addProductToFavorite(Long productId, Long userId);

    void removeProductToFavorite(Long productId, Long userId);

    List<ProductDto> getAllFavoritesProducts(Long userId);

    void updateUserDni(String dni, Long userId);

    void resendEmail(ResendRegistrationEmailRequestDto registrationEmailRequestDto) throws MessagingException;
}
