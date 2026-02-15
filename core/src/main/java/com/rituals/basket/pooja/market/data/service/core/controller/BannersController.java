package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.model.Banners;
import com.rituals.basket.pooja.market.data.service.core.service.BannersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
@RequiredArgsConstructor
@Tag(name = "banners", description = "Operations related to Banners")
public class BannersController {

    private final BannersService bannersService;

    @PostMapping("/multiple")
    @Operation(summary = "Create Multiple Items")
    public ResponseEntity<List<Banners>> createMultiple(@RequestBody List<Banners> item) {
        return ResponseEntity.ok(bannersService.createItems(item));
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

    @PutMapping("/{id}")
    @Operation(summary = "Update Banner")
    public ResponseEntity<Banners> update(
            @PathVariable Long id,
            @RequestBody Banners request
    ) {
        return ResponseEntity.ok(bannersService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Banner")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bannersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

