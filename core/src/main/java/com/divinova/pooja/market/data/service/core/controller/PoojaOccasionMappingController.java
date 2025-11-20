package com.divinova.pooja.market.data.service.core.controller;

import com.divinova.pooja.market.data.service.core.model.PoojaItemOccasionMapping;
import com.divinova.pooja.market.data.service.core.service.PoojaOccasionMappingService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/mappings")
@RequiredArgsConstructor
@Tag(name = "Pooja Item - Occasion Mapping",
        description = "Mapping operations between items and occasions")
public class PoojaOccasionMappingController {

    private final PoojaOccasionMappingService mappingService;

    @PostMapping
    @Operation(summary = "Create mapping between Item & Occasion")
    public ResponseEntity<PoojaItemOccasionMapping> create(
            @RequestParam Long itemId,
            @RequestParam Long occasionId,
            @RequestParam(required = false) String notes
    ) {
        return ResponseEntity.ok(
                mappingService.create(itemId, occasionId, notes)
        );
    }

    @GetMapping
    @Operation(summary = "List all mappings")
    public ResponseEntity<List<PoojaItemOccasionMapping>> getAll() {
        return ResponseEntity.ok(mappingService.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a mapping")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mappingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

