package com.anthony.biblioteca_virtual.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessErrorCodes {
    NO_CODE(0, "No code", HttpStatus.NOT_IMPLEMENTED),
    ACCOUNT_LOCKED(1, "Account is locked", HttpStatus.FORBIDDEN),
    ACCOUNT_DISABLED(2, "Account is disabled", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS(3, "Invalid credentials", HttpStatus.UNAUTHORIZED),
    ACCOUNT_NOT_FOUND(4, "Account not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(5, "User not found", HttpStatus.NOT_FOUND),
    PASSWORD_NOT_MATCHING(6, "Password not matching", HttpStatus.BAD_REQUEST),
    INCORRECT_PASSWORD(7, "Incorrect password", HttpStatus.BAD_REQUEST);
    private int code;
    private String description;
    private HttpStatus httpStatus;
}
