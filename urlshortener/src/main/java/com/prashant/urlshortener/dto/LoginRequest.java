package com.prashant.urlshortener.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Email required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password required")
    private String password;
}
