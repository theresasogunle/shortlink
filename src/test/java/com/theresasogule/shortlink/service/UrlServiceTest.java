package com.theresasogule.shortlink.service;

import com.theresasogule.shortlink.entity.Url;
import com.theresasogule.shortlink.repository.UrlRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @Mock
    private UrlRespository urlRespository;

    @InjectMocks
    private UrlService urlService;

    private String testUrl = "https://example.com";

    @BeforeEach
    void setUp() {
        // Reset mocks before each test
    }

    @Test
    void testShortenUrl_WithoutCustomAlias_GeneratesRandomCode() {
        // Arrange
        when(urlRespository.existsByShortCode(anyString())).thenReturn(false);
        when(urlRespository.save(any(Url.class))).thenAnswer(invocation -> {
            Url url = invocation.getArgument(0);
            url.setId(1L);
            return url;
        });

        // Act
        Url result = urlService.shortenUrl(testUrl);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getShortCode());
        assertEquals(6, result.getShortCode().length());
        assertEquals(testUrl, result.getOriginalUrl());
        assertEquals(0, result.getClickCount());
        verify(urlRespository).save(any(Url.class));
    }

    @Test
    void testShortenUrl_WithCustomAlias_UsesCustomAlias() {
        // Arrange
        String customAlias = "my-link";
        when(urlRespository.existsByShortCode(customAlias)).thenReturn(false);
        when(urlRespository.save(any(Url.class))).thenAnswer(invocation -> {
            Url url = invocation.getArgument(0);
            url.setId(1L);
            return url;
        });

        // Act
        Url result = urlService.shortenUrl(testUrl, customAlias);

        // Assert
        assertNotNull(result);
        assertEquals(customAlias, result.getShortCode());
        assertEquals(testUrl, result.getOriginalUrl());
        assertEquals(0, result.getClickCount());
        verify(urlRespository).existsByShortCode(customAlias);
        verify(urlRespository).save(any(Url.class));
    }

    @Test
    void testShortenUrl_WithExistingCustomAlias_ThrowsException() {
        // Arrange
        String customAlias = "existing-link";
        when(urlRespository.existsByShortCode(customAlias)).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            urlService.shortenUrl(testUrl, customAlias);
        });

        assertTrue(exception.getMessage().contains("already in use"));
        verify(urlRespository).existsByShortCode(customAlias);
        verify(urlRespository, never()).save(any(Url.class));
    }

    @Test
    void testShortenUrl_WithEmptyCustomAlias_GeneratesRandomCode() {
        // Arrange
        when(urlRespository.existsByShortCode(anyString())).thenReturn(false);
        when(urlRespository.save(any(Url.class))).thenAnswer(invocation -> {
            Url url = invocation.getArgument(0);
            url.setId(1L);
            return url;
        });

        // Act
        Url result = urlService.shortenUrl(testUrl, "");

        // Assert
        assertNotNull(result);
        assertNotNull(result.getShortCode());
        assertEquals(6, result.getShortCode().length());
        assertEquals(testUrl, result.getOriginalUrl());
        verify(urlRespository).save(any(Url.class));
    }

    @Test
    void testShortenUrl_WithNullCustomAlias_GeneratesRandomCode() {
        // Arrange
        when(urlRespository.existsByShortCode(anyString())).thenReturn(false);
        when(urlRespository.save(any(Url.class))).thenAnswer(invocation -> {
            Url url = invocation.getArgument(0);
            url.setId(1L);
            return url;
        });

        // Act
        Url result = urlService.shortenUrl(testUrl, null);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getShortCode());
        assertEquals(6, result.getShortCode().length());
        assertEquals(testUrl, result.getOriginalUrl());
        verify(urlRespository).save(any(Url.class));
    }
}
