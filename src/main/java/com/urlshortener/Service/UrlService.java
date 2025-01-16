package com.urlshortener.Service;

import com.urlshortener.Model.UrlEntity;
import com.urlshortener.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service

public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private static final String BASE62="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private Random random= new Random();


    //Url Shortening Logic
    public UrlEntity shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl(longUrl);
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setLongUrl(longUrl);
        urlEntity.setShortUrl(shortUrl);
        urlEntity.setCreatedAt(java.time.LocalDateTime.now());
        return urlRepository.save(urlEntity);
    }

    //Retrieve Long Url
    public Optional<UrlEntity> getUrlByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    private String generateShortUrl(String longUrl) {
        //Logic to generate short url
        StringBuilder shortUrl = new StringBuilder();
        long hashcode = longUrl.hashCode() & 0x7FFFFFFF;;
        System.out.println(hashcode);
        while (hashcode > 0) {
            shortUrl.append(BASE62.charAt((int)hashcode % 62));
            hashcode = hashcode / 62;
        }
        // Return a short url of length 7
        System.out.println(shortUrl+"Hello testing from this side" +longUrl);
       return shortUrl.length() >= 7 ? shortUrl.substring(0, 7) : shortUrl.toString();
    }


}
