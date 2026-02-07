package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "key", required = false) String key) {
        // Default key if not provided, for easy testing as requested
        if (key == null || key.isEmpty()) {
            key = "ChatGPT Image Dec 8, 2025, 08_37_15 PM.png";
        }

        // Basic sanitization to avoid path traversal if needed, though S3 keys are
        // flexible
        // For this task, we assume valid S3 keys

        try {
            byte[] imageBytes = s3Service.getImage(key);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG) // Assuming PNG based on the example
                    .body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
