package com.rituals.basket.pooja.market.data.service.core.controller;

import java.net.http.HttpHeaders;
import java.util.List;

import com.rituals.basket.pooja.market.data.service.core.model.PoojaItem;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rituals.basket.pooja.market.data.service.core.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "key", required = false) String key) {
        // Default key if not provided, for easy testing as requested
        if (key == null || key.isEmpty()) {
            key = "turmeric.png";
        }

        try {
            byte[] imageBytes = s3Service.getImage(key);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG) // Assuming PNG based on the example
                    .body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/url")
    public ResponseEntity<String> getImageUrl(@RequestParam(value = "key") String key) {
        if (key == null || key.isEmpty()) {
            return ResponseEntity.badRequest().body("Key is required");
        }
        return ResponseEntity.ok(s3Service.getImageUrl(key));
    }
}
