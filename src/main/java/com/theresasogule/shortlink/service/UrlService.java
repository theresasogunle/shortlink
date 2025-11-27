package com.theresasogule.shortlink.service;

import com.theresasogule.shortlink.entity.Url;
import com.theresasogule.shortlink.repository.UrlRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRespository urlRespository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;

    public Url shortenUrl(String originalUrl) {
        // Generate a unique short code
        String shortCode = generateUniqueShortCode();

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setClickCount(0);

        return urlRespository.save(url);

    }

    public Url getOriginalUrl(String shortCode) {
        return urlRespository.findByShortCode(shortCode)
                .orElseThrow(()-> new RuntimeException("Short URL Not Found"+ shortCode));
    }

    public void increaseClickCount(String shortCode) {
        Url url = getOriginalUrl(shortCode);
        url.setClickCount(url.getClickCount() + 1);
        urlRespository.save(url);
    }

    private String generateUniqueShortCode() {
        String shortCode;
        do {
            shortCode =generateRandomCode();
        }while(urlRespository.existsByShortCode(shortCode));
        return shortCode;
    }

    private String generateRandomCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }

        return code.toString();
    }
}
