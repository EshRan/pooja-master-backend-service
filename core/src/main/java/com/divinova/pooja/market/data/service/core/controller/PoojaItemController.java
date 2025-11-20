package com.divinova.pooja.market.data.service.core.controller;


import com.divinova.pooja.market.data.service.core.model.PoojaItem;
import com.divinova.pooja.market.data.service.core.service.PoojaItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Tag(name = "Pooja Items", description = "Operations for Pooja Items")
public class PoojaItemController {

    private final PoojaItemService poojaItemService;

    @PostMapping
    @Operation(summary = "Create a Pooja Item")
    public ResponseEntity<PoojaItem> create(@RequestBody PoojaItem item) {
        return ResponseEntity.ok(poojaItemService.createItem(item));
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

    @PutMapping("/{id}")
    @Operation(summary = "Update Pooja Item")
    public ResponseEntity<PoojaItem> update(
            @PathVariable Long id,
            @RequestBody PoojaItem item
    ) {
        return ResponseEntity.ok(poojaItemService.updateItem(id, item));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Pooja Item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        poojaItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
