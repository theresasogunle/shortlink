package com.theresasogule.shortlink.controller;

import com.theresasogule.shortlink.dto.ShortenUrlRequest;
import com.theresasogule.shortlink.dto.ShortenUrlResponse;
import com.theresasogule.shortlink.entity.Url;
import com.theresasogule.shortlink.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@Valid @RequestBody ShortenUrlRequest shortenUrlRequest) {
        Url url = urlService.shortenUrl(shortenUrlRequest.getUrl());

        String shortUrl = "http://localhost:8080/"+ url.getShortCode();

        ShortenUrlResponse response = new ShortenUrlResponse(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortCode(),
                shortUrl,
                url.getCreatedAt(),
                url.getClickCount()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @GetMapping("/stats/{shortCode}")
    public ResponseEntity<ShortenUrlResponse> getUrlStats(@PathVariable String shortCode) {
        Url url = urlService.getOriginalUrl(shortCode);
        String shortUrl = "http://localhost:8080/"+ url.getShortCode();
        ShortenUrlResponse response = new ShortenUrlResponse(
                url.getId(),
                url.getOriginalUrl(),
                url.getShortCode(),
                shortUrl,
                url.getCreatedAt(),
                url.getClickCount()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
