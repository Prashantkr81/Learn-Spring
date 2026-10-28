package com.prashant.urlshortener.service;

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

import java.security.SecureRandom;
import java.util.List;

@Service
public class ShortUrlService {

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int CODE_LENGTH = 7;

    private final ShortUrlRepository shortUrlRepository;
    private final UserRepository userRepository;
    private final SecureRandom random = new SecureRandom();

    public ShortUrlService(
            ShortUrlRepository shortUrlRepository,
            UserRepository userRepository) {
        this.shortUrlRepository = shortUrlRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ShortUrlResponse createShortUrl(
            CreateShortUrlRequest request) {

        User owner = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        String shortCode = generateUniqueCode();

        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(request.getOriginalUrl())
                .shortUrl(shortCode)
                .owner(owner)
                .build();

        ShortUrl savedUrl = shortUrlRepository.save(shortUrl);

        return ShortUrlResponse.builder()
                .id(savedUrl.getId())
                .OriginalUrl(savedUrl.getOriginalUrl())
                .shortUrl("http://localhost:8080/" + savedUrl.getShortUrl())
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

    public List<ShortUrlResponse> getUserUrls(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );
        }

        List<ShortUrl> urls =
                shortUrlRepository.findByOwner_Id(userId);

        return urls.stream()
                .map(url -> ShortUrlResponse.builder()
                        .id(url.getId())
                        .OriginalUrl(url.getOriginalUrl())
                        .shortUrl(
                                "http://localhost:8080/"
                                        + url.getShortUrl()
                        )
                        .build())
                .toList();
    }

    @Transactional
    public void deleteShortUrl(Long userId, Long shortUrlId) {

        ShortUrl shortUrl = shortUrlRepository
                .findById(shortUrlId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Short URL not found"
                        )
                );

        if (!shortUrl.getOwner().getId().equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot delete another user's short URL"
            );
        }

        shortUrlRepository.delete(shortUrl);
    }
}
