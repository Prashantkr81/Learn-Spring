package com.prashant.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShortUrlRequest {

    @NotBlank(message = "Original URL is required")
    @Size(max = 2048, message = "URL must not exceed 2048 characters")
    @Pattern(
            regexp = "^https?://.+$",
            message = "URL must start with http:// or https://"
    )
    private String originalUrl;

}
