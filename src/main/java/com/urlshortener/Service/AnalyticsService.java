package com.urlshortener.Service;

import com.urlshortener.Model.AnalyticsEntity;
import com.urlshortener.Model.UrlEntity;
import com.urlshortener.Repository.AnalyticsRepository;
import com.urlshortener.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Autowired
    private UrlRepository urlRepository;

    public void logAnalytics(String shortUrl,String referrer,String userAgent) {
        //Fetch the UrlEntity based on shortUrl
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl).orElseThrow(() -> new RuntimeException("Url not found"));

        //Create new Anlaytics record
        AnalyticsEntity analyticsEntity = new AnalyticsEntity();
        analyticsEntity.setShortUrl(urlEntity);
        analyticsEntity.setClickTime(java.time.LocalDateTime.now());
        analyticsEntity.setReferrer(referrer);
        analyticsEntity.setUserAgent(userAgent);

        //Save the record to Db
        analyticsRepository.save(analyticsEntity);
    }

}
