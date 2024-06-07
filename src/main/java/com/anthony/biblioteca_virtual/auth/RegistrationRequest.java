package com.anthony.biblioteca_virtual.auth;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name cannot be empty")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotEmpty(message = "First lastname cannot be empty")
    @NotBlank(message = "First lastname cannot be blank")
    private String lastName;

    @Email(message = "First email must be a valid email address")
    @NotEmpty(message = "First email cannot be empty")
    @NotBlank(message = "First email cannot be blank")
    private String email;

    @NotEmpty(message = "First password cannot be empty")
    @NotBlank(message = "First password cannot be blank")
    @Size(min = 8, message = "First password must be at least 8 characters long")
    private String password;

}
