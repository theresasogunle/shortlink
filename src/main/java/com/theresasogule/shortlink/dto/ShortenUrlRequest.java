package com.theresasogule.shortlink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ShortenUrlRequest {
    @NotBlank(message = "URL is required")
    @Pattern(regexp = "^https?://.+", message = "Must be a valid URL starting with http:// or https://")
    private String url;
    
    @Pattern(regexp = "^[a-zA-Z0-9-_]{3,20}$", message = "Custom alias must be 3-20 characters and contain only letters, numbers, hyphens, and underscores")
    private String customAlias;
}
