
package com.prashant.urlshortener.controller;

import com.prashant.urlshortener.dto.CreateShortUrlRequest;
import com.prashant.urlshortener.dto.ShortUrlResponse;
import com.prashant.urlshortener.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    // Create a short URL
    @PostMapping("/api/urls")
    public ResponseEntity<ShortUrlResponse> createShortUrl(
            @Valid @RequestBody CreateShortUrlRequest request,
            Authentication authentication) {

        ShortUrlResponse response =
                shortUrlService.createShortUrl(request, authentication.getName());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Redirect to the original URL
    @GetMapping("/{shortCode:[a-zA-Z0-9]+}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String originalUrl =
                shortUrlService.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }

    @GetMapping("/api/urls/me")
    public ResponseEntity<List<ShortUrlResponse>> getUserUrls(
            Authentication authentication) {

        List<ShortUrlResponse> urls =
                shortUrlService.getUserUrls(authentication.getName());

        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/api/urls/{shortUrlId}")
    public ResponseEntity<Void> deleteShortUrl(
            @PathVariable Long shortUrlId,
            Authentication authentication) {

        shortUrlService.deleteShortUrl(authentication.getName(), shortUrlId);

        return ResponseEntity.noContent().build();
    }
}
