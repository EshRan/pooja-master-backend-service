package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.model.PoojaItem;
import com.rituals.basket.pooja.market.data.service.core.repository.PoojaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class PoojaItemService {

    private final PoojaItemRepository poojaItemRepository;

    public PoojaItem getItem(Long id) {
        return poojaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found: " + id));
    }

    public List<PoojaItem> getAllItems() {
        return poojaItemRepository.findAll();
    }

    public PoojaItem saveOrUpdateItem(Long id, PoojaItem updated) {

        if (id == null) {
            return createItem(updated);
        } else {
            return updateItem(id, updated);
        }
    }

    public PoojaItem createItem(PoojaItem updated) {

        validateCreateRequest(updated);

        PoojaItem entity = new PoojaItem();
        mapAllFields(entity, updated);

        return poojaItemRepository.save(entity);
    }

    private PoojaItem updateItem(Long id, PoojaItem updated) {

        PoojaItem existing = poojaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pooja Item not found with id: " + id));

        mapNonNullFields(existing, updated);

        return poojaItemRepository.save(existing);
    }

    private void validateCreateRequest(PoojaItem item) {

        if (item.getItemName() == null ||
                item.getItemCode() == null ||
                item.getDescription() == null ||
                item.getTotalQuantity() == null ||
                item.getQuantityUnit() == null ||
                item.getIsInStock() == null ||
                item.getStockInQuantity() == null) {

            throw new IllegalArgumentException("All fields are required for creating a new item");
        }
    }

    private void mapAllFields(PoojaItem target, PoojaItem source) {

        target.setItemName(source.getItemName());
        target.setItemCode(source.getItemCode());
        target.setDescription(source.getDescription());
        target.setTotalQuantity(source.getTotalQuantity());
        target.setS3ImageKey(source.getS3ImageKey());
        target.setIsInStock(source.getIsInStock());
        target.setStockInQuantity(source.getStockInQuantity());
        target.setPrice(source.getPrice());
    }

    private void mapNonNullFields(PoojaItem target, PoojaItem source) {

        if (source.getItemName() != null) {
            target.setItemName(source.getItemName());
        }

        if (source.getItemCode() != null) {
            target.setItemCode(source.getItemCode());
        }

        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }

        if (source.getTotalQuantity() != null) {
            target.setTotalQuantity(source.getTotalQuantity());
        }

        if (source.getQuantityUnit() != null) {
            target.setQuantityUnit(source.getQuantityUnit());
        }

        if (source.getS3ImageKey() != null) {
            target.setS3ImageKey(source.getS3ImageKey());
        }

        if (source.getIsInStock() != null) {
            target.setIsInStock(source.getIsInStock());
        }

        if (source.getStockInQuantity() != null) {
            target.setStockInQuantity(source.getStockInQuantity());
        }

        if (source.getPrice() != null) {
            target.setPrice(source.getPrice());
        }
    }

    public List<PoojaItem> createItems(List<PoojaItem> item) {
        return item.stream()
                .map(poojaItem -> saveOrUpdateItem(poojaItem.getId(), poojaItem))
                .toList();
    }

    public void deleteItem(Long id) {
        poojaItemRepository.deleteById(id);
    }
}
