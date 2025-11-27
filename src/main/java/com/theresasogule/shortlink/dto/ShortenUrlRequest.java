package com.theresasogule.shortlink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ShortenUrlRequest {
    @NotBlank(message = "URL is required")
    @Pattern(regexp = "^https?://.+", message = "Must be a valid URL starting with http:// or https://")
    private String url;
}
