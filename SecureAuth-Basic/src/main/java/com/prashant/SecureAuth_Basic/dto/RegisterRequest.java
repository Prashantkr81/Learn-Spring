package com.prashant.SecureAuth_Basic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username Required")
    private String username;

    @NotBlank(message = "Password Required")
    @Size(min = 6, message = "Password must be at least of 6 characters")
    private String password;

}
