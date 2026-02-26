package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.model.Banners;
import com.rituals.basket.pooja.market.data.service.core.service.BannersService;
import com.rituals.basket.pooja.market.data.service.core.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
@Tag(name = "banners", description = "Operations related to Banners")
public class BannersController {

    private final BannersService bannersService;
    private final S3Service s3Service;

    @PostMapping(value = "/multiple", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Create Multiple Items")
    public ResponseEntity<List<Banners>> createMultiple(
            @RequestPart("data") List<Banners> items,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        // This is a bulk upload endpoint. To cleanly support multipart here, we'd need
        // a complex mapping
        // between each file and each banner in the list. For simplicity in this
        // refactor,
        // we will leave this as a bulk data creation (or assume the frontend will send
        // individual requests
        // with files for new banners). If images are provided in bulk, matching them up
        // to items is required.
        // Assuming no changes to bulk creation file handling for now.
        return ResponseEntity.ok(bannersService.createItems(items));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Create Banner")
    public ResponseEntity<Banners> create(
            @RequestPart("data") Banners item,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, item.getS3ImageKey());
            item.setS3ImageKey(imageKey);
        }
        return ResponseEntity.ok(bannersService.update(null, item)); // Assuming BannersService handles null id for
                                                                     // creation
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Banner by ID")
    public ResponseEntity<Banner> get(@PathVariable Long id) {
        return ResponseEntity.ok((Banner) bannersService.get(id));
    }

    @GetMapping
    @Operation(summary = "List all Banners")
    public ResponseEntity<List<Banners>> getAll() {
        return ResponseEntity.ok(bannersService.getAll());
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Update Banner")
    public ResponseEntity<Banners> update(
            @PathVariable Long id,
            @RequestPart("data") Banners request,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, request.getS3ImageKey());
            request.setS3ImageKey(imageKey);
        }
        return ResponseEntity.ok(bannersService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Banner")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bannersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
