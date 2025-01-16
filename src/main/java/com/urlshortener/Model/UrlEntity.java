package com.urlshortener.Model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="urls")
@NoArgsConstructor
public class UrlEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 500)
    private String longUrl;
    @Column(nullable = false,unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }




}
