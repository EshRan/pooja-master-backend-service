package com.divinova.core.service;


import com.divinova.core.model.PoojaItem;
import com.divinova.core.repository.PoojaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoojaItemService {

    private final PoojaItemRepository poojaItemRepository;

    public PoojaItem createItem(PoojaItem item) {
        return poojaItemRepository.save(item);
    }

    public PoojaItem getItem(Long id) {
        return poojaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found: " + id));
    }

    public List<PoojaItem> getAllItems() {
        return poojaItemRepository.findAll();
    }

    public PoojaItem updateItem(Long id, PoojaItem updated) {
        PoojaItem existing = getItem(id);

        existing.setItemName(updated.getItemName());
        existing.setItemCode(updated.getItemCode());
        existing.setDescription(updated.getDescription());
        existing.setEstimatedQuantity(updated.getEstimatedQuantity());
        existing.setQuantityUnit(updated.getQuantityUnit());
        existing.setS3ImageKey(updated.getS3ImageKey());
        existing.setIsActive(updated.getIsActive());

        return poojaItemRepository.save(existing);
    }

    public void deleteItem(Long id) {
        poojaItemRepository.deleteById(id);
    }
}

