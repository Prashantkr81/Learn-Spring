package com.prashant.urlshortener.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponse {

    private Long id;
    private String name;
    private String email;
    private String message;
}
