package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.model.PoojaItem;
import com.rituals.basket.pooja.market.data.service.core.service.PoojaItemService;
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
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Tag(name = "Pooja Items", description = "Operations for Pooja Items")
public class PoojaItemController {

    private final PoojaItemService poojaItemService;
    private final S3Service s3Service;

    // create pooja item
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Create a Pooja Item")
    public ResponseEntity<PoojaItem> create(
            @RequestPart("data") PoojaItem item,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, item.getS3ImageKey());
            item.setS3ImageKey(imageKey);
        }
        return ResponseEntity.ok(poojaItemService.createItem(item));
    }

    @PostMapping("/multiple")
    @Operation(summary = "Create Multiple Items")
    public ResponseEntity<List<PoojaItem>> createMultiple(@RequestBody List<PoojaItem> item) {
        return ResponseEntity.ok(poojaItemService.createItems(item));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Pooja Item by ID")
    public ResponseEntity<PoojaItem> get(@PathVariable Long id) {
        return ResponseEntity.ok(poojaItemService.getItem(id));
    }

    @GetMapping
    @Operation(summary = "Get all Pooja Items")
    public ResponseEntity<List<PoojaItem>> getAll() {
        return ResponseEntity.ok(poojaItemService.getAllItems());
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Update Pooja Item")
    public ResponseEntity<PoojaItem> update(
            @PathVariable Long id,
            @RequestPart("data") PoojaItem item,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imageKey = s3Service.uploadFile(image, item.getS3ImageKey());
            item.setS3ImageKey(imageKey);
        }
        return ResponseEntity.ok(poojaItemService.saveOrUpdateItem(id, item));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Pooja Item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        poojaItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
