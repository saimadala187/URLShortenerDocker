package com.urlshortener.Repository;

import com.urlshortener.Model.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity,Long> {
    Optional<UrlEntity> findByShortUrl(String shortUrl);
    //Optional<UrlEntity> findByLongUrl(String longUrl);
}
