package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.model.PoojaItemOccasionMapping;
import com.rituals.basket.pooja.market.data.service.core.service.PoojaOccasionMappingService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/mappings")
@RequiredArgsConstructor
@Tag(name = "Pooja Item - Occasion Mapping", description = "Mapping operations between items and occasions")
public class PoojaOccasionMappingController {

    private final PoojaOccasionMappingService mappingService;

    @PostMapping
    @Operation(summary = "Create mapping between Item & Occasion")
    public ResponseEntity<PoojaItemOccasionMapping> create(
            @RequestParam Long itemId,
            @RequestParam Long occasionId,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) Integer quantity) {
        return ResponseEntity.ok(
                mappingService.create(itemId, occasionId, notes, quantity));
    }

    @GetMapping
    @Operation(summary = "List all mappings")
    public ResponseEntity<List<PoojaItemOccasionMapping>> getAll() {
        return ResponseEntity.ok(mappingService.getAll());
    }

    @GetMapping("/occasion/{occasionId}")
    @Operation(summary = "Get mappings by occasion ID")
    public ResponseEntity<List<PoojaItemOccasionMapping>> getByOccasionId(@PathVariable Long occasionId) {
        return ResponseEntity.ok(mappingService.getByOccasionId(occasionId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a mapping")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mappingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
