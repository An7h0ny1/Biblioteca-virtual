package com.anthony.biblioteca_virtual.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {
    @Email(message = "Email must be a valid email address")
    @NotEmpty(message = "First email cannot be empty")
    @NotBlank(message = "First email cannot be blank")
    private String email;

    @NotEmpty(message = "First password cannot be empty")
    @NotBlank(message = "First password cannot be blank")
    @Size(min = 8, message = "First password must be at least 8 characters long")
    private String password;

}
