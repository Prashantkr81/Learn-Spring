package com.prashant.urlshortener.service;

import com.prashant.urlshortener.config.AppProperties;
import com.prashant.urlshortener.dto.CreateShortUrlRequest;
import com.prashant.urlshortener.dto.ShortUrlResponse;
import com.prashant.urlshortener.entity.ShortUrl;
import com.prashant.urlshortener.entity.User;
import com.prashant.urlshortener.repository.ShortUrlRepository;
import com.prashant.urlshortener.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.security.SecureRandom;
import java.util.List;

@Service
public class ShortUrlService {

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int CODE_LENGTH = 7;

    private final ShortUrlRepository shortUrlRepository;
    private final UserRepository userRepository;
    private final AppProperties appProperties;
    private final SecureRandom random = new SecureRandom();

    public ShortUrlService(
            ShortUrlRepository shortUrlRepository,
            UserRepository userRepository,
            AppProperties appProperties) {
        this.shortUrlRepository = shortUrlRepository;
        this.userRepository = userRepository;
        this.appProperties = appProperties;
    }

    @Transactional
    public ShortUrlResponse createShortUrl(
            CreateShortUrlRequest request, String email) {

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED, "User not found"
                        ));

        String shortCode = generateUniqueCode();

        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(request.getOriginalUrl())
                .shortUrl(shortCode)
                .owner(owner)
                .build();

        ShortUrl savedUrl = shortUrlRepository.save(shortUrl);

        return ShortUrlResponse.builder()
                .id(savedUrl.getId())
                .originalUrl(savedUrl.getOriginalUrl())
                .shortUrl(appProperties.getBaseUrl() + "/" + savedUrl.getShortUrl())
                .createdAt(savedUrl.getCreatedAt())
                .updatedAt(savedUrl.getUpdatedAt())
                .build();
    }

    private String generateUniqueCode() {
        String code;

        do {
            StringBuilder builder = new StringBuilder(CODE_LENGTH);

            for (int i = 0; i < CODE_LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                builder.append(CHARACTERS.charAt(index));
            }

            code = builder.toString();

        } while (shortUrlRepository.existsByShortUrl(code));

        return code;
    }

    public String getOriginalUrl(String shortCode) {

        ShortUrl shortUrl= shortUrlRepository
                .findByShortUrl(shortCode)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No such shortUrl"
                        )
                        );
        return shortUrl.getOriginalUrl();
    }

    @Transactional(readOnly = true)
    public Page<ShortUrlResponse> getUserUrls(
            String email,
            int page,
            int size,
            String sortBy,
            String direction) {

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "User not found"
                ));

        // Prevent invalid page size
        if (page < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number cannot be negative"
            );
        }

        if (size < 1 || size > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page size must be between 1 and 100"
            );
        }

        // Allow only supported sorting fields
        if (!List.of("id", "createdAt", "updatedAt").contains(sortBy)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid sort field"
            );
        }


        if (!"asc".equalsIgnoreCase(direction)
                && !"desc".equalsIgnoreCase(direction)) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Direction must be asc or desc"
            );
        }

        Sort.Direction sortDirection =
                "asc".equalsIgnoreCase(direction)
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC;

        Sort sort = Sort.by(
                new Sort.Order(sortDirection, sortBy),
                Sort.Order.asc("id")
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ShortUrl> urls =
                shortUrlRepository.findByOwner_Id(owner.getId(), pageable);

        return urls.map(url -> ShortUrlResponse.builder()
                .id(url.getId())
                .originalUrl(url.getOriginalUrl())
                .shortUrl(appProperties.getBaseUrl() + "/" + url.getShortUrl())
                .createdAt(url.getCreatedAt())
                .updatedAt(url.getUpdatedAt())
                .build());
    }

    @Transactional
    public void deleteShortUrl(String email, Long shortUrlId) {

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "User not found"
                ));

        ShortUrl shortUrl = shortUrlRepository
                .findById(shortUrlId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Short URL not found"
                        )
                );

        if (!shortUrl.getOwner().getId().equals(owner.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot delete another user's short URL"
            );
        }

        shortUrlRepository.delete(shortUrl);
    }
}
