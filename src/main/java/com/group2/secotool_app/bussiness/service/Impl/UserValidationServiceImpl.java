package com.group2.secotool_app.bussiness.service.Impl;

import com.group2.secotool_app.bussiness.service.IUserService;
import com.group2.secotool_app.bussiness.service.IUserValidationService;
import com.group2.secotool_app.model.dto.request.UserAuthenticationRequestDto;
import com.group2.secotool_app.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements IUserValidationService {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    @Override
    public void AuthenticateUser(UserAuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequestDto.username(),authenticationRequestDto.password())
        );
    }

    @Override
    public Optional<User> isUserAuthenticated() {
        var userCredential = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!userCredential.equals("anonymousUser")){
            Long userId = Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
            return Optional.of(userService.findUserById(userId));
        }
        return Optional.empty();
    }

    @Override
    public void isUsernameAvailable(String username) {
        if (userService.existsByUsername(username))
            throw new RuntimeException("username "+ username + " not available");
    }
}
