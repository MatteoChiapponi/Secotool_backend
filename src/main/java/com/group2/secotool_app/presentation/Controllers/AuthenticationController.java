package com.group2.secotool_app.presentation.Controllers;

import com.group2.secotool_app.bussiness.facade.IUserFacade;
import com.group2.secotool_app.model.dto.UserAuthenticatedResponseDto;
import com.group2.secotool_app.model.dto.request.ResendRegistrationEmailRequestDto;
import com.group2.secotool_app.model.dto.request.UserAuthenticationRequestDto;
import com.group2.secotool_app.model.dto.request.UserRegistrationRequestDto;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IUserFacade userFacade;

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticatedResponseDto> AuthenticateUser(@RequestBody @Valid UserAuthenticationRequestDto authenticationRequest) {
        return ResponseEntity.ok(userFacade.authenticateUser(authenticationRequest));
    }

    //enviar email de forma asincrona
    @PostMapping("/resend_email")
    public ResponseEntity<String> resendEmail(@RequestBody @Valid ResendRegistrationEmailRequestDto resendRegistrationEmailRequestDto) throws MessagingException {
        userFacade.resendEmail(resendRegistrationEmailRequestDto);
        return ResponseEntity.ok("email successfully sent");
    }

    @PostMapping("/singup")
    public ResponseEntity<String> registerNewUser(@RequestBody @Valid UserRegistrationRequestDto registrationRequestDto) throws MessagingException {
        userFacade.registerUser(registrationRequestDto);
        return ResponseEntity.ok("user successfully registered");
    }

}
