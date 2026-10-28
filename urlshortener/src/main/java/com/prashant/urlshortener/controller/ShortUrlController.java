
package com.prashant.urlshortener.controller;

import com.prashant.urlshortener.dto.CreateShortUrlRequest;
import com.prashant.urlshortener.dto.ShortUrlResponse;
import com.prashant.urlshortener.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            @Valid @RequestBody CreateShortUrlRequest request) {

        ShortUrlResponse response =
                shortUrlService.createShortUrl(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Redirect to the original URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String originalUrl =
                shortUrlService.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }

    @GetMapping("/api/urls/user/{userId}")
    public ResponseEntity<List<ShortUrlResponse>> getUserUrls(
            @PathVariable Long userId) {

        List<ShortUrlResponse> urls =
                shortUrlService.getUserUrls(userId);

        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/api/urls/{shortUrlId}/user/{userId}")
    public ResponseEntity<Void> deleteShortUrl(
            @PathVariable Long shortUrlId,
            @PathVariable Long userId) {

        shortUrlService.deleteShortUrl(userId, shortUrlId);

        return ResponseEntity.noContent().build();
    }
}
