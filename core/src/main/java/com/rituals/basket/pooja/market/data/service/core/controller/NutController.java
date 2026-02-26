package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.model.Nut;
import com.rituals.basket.pooja.market.data.service.core.service.NutService;
import com.rituals.basket.pooja.market.data.service.core.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/nuts")
@RequiredArgsConstructor
@Tag(name = "Nuts", description = "Operations for Nuts")
public class NutController {

    private final NutService nutService;
    private final S3Service s3Service;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Create a Nut Item")
    public ResponseEntity<Nut> create(
            @RequestPart("data") Nut item,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, item.getS3ImageKey());
            item.setS3ImageKey(imageKey);
        }
        return ResponseEntity.ok(nutService.createItem(item));
    }

    @PostMapping("/multiple")
    @Operation(summary = "Create Multiple Nut Items")
    public ResponseEntity<List<Nut>> createMultiple(@RequestBody List<Nut> items) {
        return ResponseEntity.ok(nutService.createItems(items));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Nut Item by ID")
    public ResponseEntity<Nut> get(@PathVariable Long id) {
        return ResponseEntity.ok(nutService.getItem(id));
    }

    @GetMapping
    @Operation(summary = "Get all Nut Items")
    public ResponseEntity<List<Nut>> getAll() {
        return ResponseEntity.ok(nutService.getAllItems());
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Update Nut Item")
    public ResponseEntity<Nut> update(
            @PathVariable Long id,
            @RequestPart("data") Nut item,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, item.getS3ImageKey());
            item.setS3ImageKey(imageKey);
        }
        return ResponseEntity.ok(nutService.saveOrUpdateItem(id, item));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Nut Item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nutService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
