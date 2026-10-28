package com.prashant.urlshortener.repository;

import com.prashant.urlshortener.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    Optional<ShortUrl> findByShortUrl(String shortUrl);

    boolean existsByShortUrl(String shortUrl);

    List<ShortUrl> findByOwner_Id(Long userId);
}
