package com.urlshortener.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table (name="analytics")
public class AnalyticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UrlEntity getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(UrlEntity shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getClickTime() {
        return clickTime;
    }

    public void setClickTime(LocalDateTime clickTime) {
        this.clickTime = clickTime;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @ManyToOne
    @JoinColumn(name="short_url_id",nullable = false)
    private UrlEntity shortUrl;

    @Column (nullable = false)
    private LocalDateTime clickTime;

    @Column
    private String referrer;

    @Column
    private String userAgent;


}
