package com.group2.secotool_app.presentation.Advice;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(runtimeException.getMessage());
    }

/*
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        System.out.println(ex.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: one of the fields already exists");
    }
*/

    @ExceptionHandler(MessagingException.class)
    public void handleMessagingException(MessagingException exception){
        System.out.println(exception.getCause());
        System.out.println(exception.getMessage());
        System.out.println("manejando MessagingException");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> clientErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            clientErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            System.out.println(fieldError.getObjectName());
        });
        return ResponseEntity.badRequest().body(clientErrors);
    }


}
