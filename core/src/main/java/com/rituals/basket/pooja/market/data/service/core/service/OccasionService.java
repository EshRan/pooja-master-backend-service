package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.model.Occasion;
import com.rituals.basket.pooja.market.data.service.core.model.PoojaItem;
import com.rituals.basket.pooja.market.data.service.core.repository.OccasionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OccasionService {

    private final OccasionRepository occasionRepository;

    public Occasion create(Occasion occasion) {
        return occasionRepository.save(occasion);
    }

    public Occasion get(Long id) {
        return occasionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Occasion not found: " + id));
    }

    public List<Occasion> getAll() {
        return occasionRepository.findAll();
    }

    public Occasion update(Long id, Occasion request) {
        Occasion existing = new Occasion();
        if(id !=null) {
            Optional<Occasion> existingRecord = occasionRepository.findById(id);
            if (existingRecord.isPresent()) {
                existing = existingRecord.get();
            }
        }

        existing.setOccasionName(request.getOccasionName());
        existing.setOccasionCode(request.getOccasionCode());
        existing.setDescription(request.getDescription());
        existing.setIsActive(request.getIsActive());

        return occasionRepository.save(existing);
    }

    public void delete(Long id) {
        occasionRepository.deleteById(id);
    }

    public List<Occasion> createItems(List<Occasion> occasions) {
        return occasions.stream()
                .map(Occasion-> update((Occasion.getId()), Occasion))
                .toList();
    }
}
