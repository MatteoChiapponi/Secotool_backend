package com.group2.secotool_app.bussiness.service;

import com.group2.secotool_app.model.dto.request.UserAuthenticationRequestDto;
import com.group2.secotool_app.model.entity.User;

import java.util.Optional;

public interface IUserValidationService {
    void AuthenticateUser (UserAuthenticationRequestDto authenticationRequestDto);
    Optional<User> isUserAuthenticated();

    void isUsernameAvailable(String username);
}
