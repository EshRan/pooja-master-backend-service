package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.model.Occasion;
import com.rituals.basket.pooja.market.data.service.core.service.OccasionService;
import com.rituals.basket.pooja.market.data.service.core.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/occasions")
@RequiredArgsConstructor
@Tag(name = "Occasions", description = "Operations related to Occasions")
public class OccasionController {

    private final OccasionService occasionService;
    private final S3Service s3Service;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Create Occasion")
    public ResponseEntity<Occasion> create(
            @RequestPart("data") Occasion request,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, request.getS3ImageKey());
            request.setS3ImageKey(imageKey);
        }
        Occasion created = occasionService.saveOrUpdate(null, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create or Update Multiple Occasions")
    public ResponseEntity<List<Occasion>> saveAll(@RequestBody List<Occasion> occasions) {
        return ResponseEntity.ok(occasionService.saveAll(occasions));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Occasion by ID")
    public ResponseEntity<Occasion> get(@PathVariable Long id) {
        return ResponseEntity.ok(occasionService.get(id));
    }

    @GetMapping
    @Operation(summary = "List all Occasions (optional category and isActive filters)")
    public ResponseEntity<List<Occasion>> getAll(
            @Parameter(description = "Category of the occasion (e.g., FESTIVE, MARRIAGE)") @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean isActive) {

        if (category != null && !category.isBlank() && isActive != null) {
            return ResponseEntity.ok(occasionService.getByCategoryAndIsActive(category, isActive));
        } else if (category != null && !category.isBlank()) {
            return ResponseEntity.ok(occasionService.getByCategory(category));
        } else if (isActive != null) {
            return ResponseEntity.ok(occasionService.getByIsActive(isActive));
        }

        return ResponseEntity.ok(occasionService.getAll());
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Update Occasion")
    public ResponseEntity<Occasion> update(
            @PathVariable Long id,
            @RequestPart("data") Occasion request,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, request.getS3ImageKey());
            request.setS3ImageKey(imageKey);
        }
        return ResponseEntity.ok(occasionService.saveOrUpdate(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Occasion")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        occasionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}