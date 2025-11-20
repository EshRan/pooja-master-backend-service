package com.divinova.pooja.market.data.service.core.service;

import com.divinova.pooja.market.data.service.core.model.Occasion;
import com.divinova.pooja.market.data.service.core.model.PoojaItem;
import com.divinova.pooja.market.data.service.core.model.PoojaItemOccasionMapping;
import com.divinova.pooja.market.data.service.core.repository.OccasionRepository;
import com.divinova.pooja.market.data.service.core.repository.PoojaItemOccasionMappingRepository;
import com.divinova.pooja.market.data.service.core.repository.PoojaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoojaOccasionMappingService {

    private final PoojaItemOccasionMappingRepository mappingRepo;
    private final PoojaItemRepository itemRepo;
    private final OccasionRepository occasionRepo;

    public PoojaItemOccasionMapping create(Long itemId, Long occasionId, String notes) {

        PoojaItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Occasion occasion = occasionRepo.findById(occasionId)
                .orElseThrow(() -> new RuntimeException("Occasion not found"));

        PoojaItemOccasionMapping mapping = PoojaItemOccasionMapping.builder()
                .poojaItem(item)
                .occasion(occasion)
                .notes(notes)
                .isActive(true)
                .build();

        return mappingRepo.save(mapping);
    }

    public List<PoojaItemOccasionMapping> getAll() {
        return mappingRepo.findAll();
    }

    public void delete(Long id) {
        mappingRepo.deleteById(id);
    }
}

