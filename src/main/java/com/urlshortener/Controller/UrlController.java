package com.urlshortener.Controller;

import com.urlshortener.Model.UrlEntity;
import com.urlshortener.Service.AnalyticsService;
import com.urlshortener.Service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    @Autowired
    private UrlService urlService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    //Url Shortening Endpoint.
    @PostMapping("/shorten")
    public UrlEntity shortenUrl(@RequestBody String longUrl) {

        return urlService.shortenUrl(longUrl);
    }

    //Url redirection
    @GetMapping("/{shortUrl}")
    @Retryable(value = RedisConnectionFailureException.class, maxAttempts = 5, backoff = @Backoff(delay = 2000))

    public String redirecToLongUrl(@PathVariable String shortUrl, HttpServletRequest request){

        System.out.println("Short URL: "+shortUrl);
        // Check if the short URL exists in Redis cache
        redisTemplate.opsForValue().set("test", "test-value");
        String value = (String) redisTemplate.opsForValue().get("test");
        System.out.println(value);
        String longUrl = (String)redisTemplate.opsForValue().get(shortUrl);
        System.out.println("Long URL: "+longUrl);
        if (longUrl!=null){
            //Log the analytics
            String referrer = request.getHeader("referer");
            String userAgent = request.getHeader("User-Agent");
            analyticsService.logAnalytics(shortUrl,referrer,userAgent);
            return longUrl;
        }

       else { //Log the analytics
            String referrer = request.getHeader("referer");
            String userAgent = request.getHeader("User-Agent");
            analyticsService.logAnalytics(shortUrl, referrer, userAgent);

            //Retrieve the long url and redirect

            UrlEntity urlEntity = urlService.getUrlByShortUrl(shortUrl).orElseThrow(() -> new RuntimeException("Url not found"));

            if (urlEntity != null) {
                // Store the result in cache for future requests
                redisTemplate.opsForValue().set(shortUrl, urlEntity.getLongUrl());
                return urlEntity.getLongUrl();
            }
        }
        return null;

    }
}
