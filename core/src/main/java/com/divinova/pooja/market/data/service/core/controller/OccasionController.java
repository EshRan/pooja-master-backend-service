package com.divinova.pooja.market.data.service.core.controller;

import com.divinova.pooja.market.data.service.core.model.Occasion;
import com.divinova.pooja.market.data.service.core.service.OccasionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occasions")
@RequiredArgsConstructor
@Tag(name = "Occasions", description = "Operations related to Occasions")
public class OccasionController {

    private final OccasionService occasionService;

    @PostMapping
    @Operation(summary = "Create Occasion")
    public ResponseEntity<Occasion> create(@RequestBody Occasion request) {
        return ResponseEntity.ok(occasionService.create(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Occasion by ID")
    public ResponseEntity<Occasion> get(@PathVariable Long id) {
        return ResponseEntity.ok(occasionService.get(id));
    }

    @GetMapping
    @Operation(summary = "List all Occasions")
    public ResponseEntity<List<Occasion>> getAll() {
        return ResponseEntity.ok(occasionService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Occasion")
    public ResponseEntity<Occasion> update(
            @PathVariable Long id,
            @RequestBody Occasion request
    ) {
        return ResponseEntity.ok(occasionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Occasion")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        occasionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

