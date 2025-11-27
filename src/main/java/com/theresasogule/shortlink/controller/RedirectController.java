package com.theresasogule.shortlink.controller;

import com.theresasogule.shortlink.entity.Url;
import com.theresasogule.shortlink.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final UrlService urlService;

    @GetMapping("/{shortCode}")
    public void redirectToOriginalUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        Url url = urlService.getOriginalUrl(shortCode);
        urlService.increaseClickCount(shortCode);
        response.sendRedirect(url.getOriginalUrl());
    }
}
