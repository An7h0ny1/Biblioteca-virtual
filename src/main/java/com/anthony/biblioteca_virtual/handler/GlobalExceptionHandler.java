package com.anthony.biblioteca_virtual.handler;

import com.anthony.biblioteca_virtual.exception.OperationNotPermittedException;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException e) {
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.ACCOUNT_LOCKED.getCode())
                .businessErrorDescription(BusinessErrorCodes.ACCOUNT_LOCKED.getDescription())
                .error(e.getMessage())
                .build());
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException e) {
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.ACCOUNT_DISABLED.getCode())
                .businessErrorDescription(BusinessErrorCodes.ACCOUNT_DISABLED.getDescription())
                .error(e.getMessage())
                .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException e) {
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder()
                .businessErrorCode(BusinessErrorCodes.INVALID_CREDENTIALS.getCode())
                .businessErrorDescription(BusinessErrorCodes.INVALID_CREDENTIALS.getDescription())
                .error(BusinessErrorCodes.INVALID_CREDENTIALS.getDescription())
                .build());
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException e) {
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder()
                .error(e.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e) {
        Set<String> errors = new HashSet<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.builder()
                .validationErrors(errors)
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {

        e.printStackTrace();
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .businessErrorDescription("Internal server error")
                .error(e.getMessage())
                .build());
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException e) {

        e.printStackTrace();
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.builder()
                .businessErrorDescription("Internal server error")
                .error(e.getMessage())
                .build());
    }
}
