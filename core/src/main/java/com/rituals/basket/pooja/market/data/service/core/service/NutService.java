package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.model.Nut;
import com.rituals.basket.pooja.market.data.service.core.repository.NutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NutService {

    private final NutRepository nutRepository;

    public Nut createItem(Nut item) {
        return nutRepository.save(item);
    }

    public Nut getItem(Long id) {
        return nutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found: " + id));
    }

    public List<Nut> getAllItems() {
        return nutRepository.findAll();
    }

    public Nut saveOrUpdateItem(Long id, Nut updated) {
        Nut existing = new Nut();
        if (id != null) {
            Optional<Nut> existingRecord = nutRepository.findById(id);
            if (existingRecord.isPresent()) {
                existing = existingRecord.get();
            }
        }
        existing.setItemName(updated.getItemName());
        existing.setItemCode(updated.getItemCode());
        existing.setDescription(updated.getDescription());
        existing.setTotalQuantity(updated.getTotalQuantity());
        existing.setQuantityUnit(updated.getQuantityUnit());
        existing.setPrice(updated.getPrice());

        if (updated.getS3ImageKey() != null) {
            existing.setS3ImageKey(updated.getS3ImageKey());
        }

        existing.setIsInStock(updated.getIsInStock());
        existing.setStockInQuantity(updated.getStockInQuantity());

        return nutRepository.save(existing);
    }

    public void deleteItem(Long id) {
        nutRepository.deleteById(id);
    }

    public List<Nut> createItems(List<Nut> items) {
        return items.stream()
                .map(nut -> saveOrUpdateItem(nut.getId(), nut))
                .toList();
    }
}
