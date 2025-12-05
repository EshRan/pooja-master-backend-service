package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.model.Occasion;
import com.rituals.basket.pooja.market.data.service.core.repository.OccasionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Occasion existing = get(id);

        existing.setOccasionName(request.getOccasionName());
        existing.setOccasionCode(request.getOccasionCode());
        existing.setDescription(request.getDescription());
        existing.setIsActive(request.getIsActive());

        return occasionRepository.save(existing);
    }

    public void delete(Long id) {
        occasionRepository.deleteById(id);
    }
}
