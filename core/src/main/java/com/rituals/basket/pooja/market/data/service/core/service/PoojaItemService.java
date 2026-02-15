package com.rituals.basket.pooja.market.data.service.core.service;


import com.rituals.basket.pooja.market.data.service.core.model.PoojaItem;
import com.rituals.basket.pooja.market.data.service.core.repository.PoojaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PoojaItem saveOrUpdateItem(Long id, PoojaItem updated) {
        PoojaItem existing = new PoojaItem();
        if(id !=null) {
            Optional<PoojaItem> existingRecord = poojaItemRepository.findById(id);
            if (existingRecord.isPresent()) {
                existing = existingRecord.get();
            }
        }
        existing.setItemName(updated.getItemName());
        existing.setItemCode(updated.getItemCode());
        existing.setDescription(updated.getDescription());
        existing.setEstimatedQuantity(updated.getEstimatedQuantity());
        existing.setQuantityUnit(updated.getQuantityUnit());
        existing.setS3ImageKey(updated.getS3ImageKey());
        existing.setIsInStock(updated.getIsInStock());

        return poojaItemRepository.save(existing);
    }


    public void deleteItem(Long id) {
        poojaItemRepository.deleteById(id);
    }

    public List<PoojaItem> createItems(List<PoojaItem> item) {
        return item.stream()
                .map(poojaItem -> saveOrUpdateItem(poojaItem.getId(), poojaItem))
                .toList();
    }
}

