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

    // =========================================================
    // ENTRY METHOD (CREATE OR UPDATE)
    // =========================================================

    public Occasion saveOrUpdate(Long id, Occasion request) {

        if (id == null) {
            return createOccasion(request);
        } else {
            return updateOccasion(id, request);
        }
    }

    // =========================================================
    // CREATE
    // =========================================================

    private Occasion createOccasion(Occasion request) {

        validateCreateRequest(request);

        Occasion occasion = new Occasion();
        mapAllFields(occasion, request);

        return occasionRepository.save(occasion);
    }

    // =========================================================
    // UPDATE
    // =========================================================

    private Occasion updateOccasion(Long id, Occasion request) {

        Occasion existing = occasionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Occasion not found with id: " + id));

        mapNonNullFields(existing, request);

        return occasionRepository.save(existing);
    }

    // =========================================================
    // GET METHODS
    // =========================================================

    public Occasion get(Long id) {
        return occasionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Occasion not found: " + id));
    }

    public List<Occasion> getAll() {
        return occasionRepository.findAll();
    }

    public List<Occasion> getByCategory(String category) {
        return occasionRepository.findByCategory(category);
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void delete(Long id) {
        if (!occasionRepository.existsById(id)) {
            throw new RuntimeException("Occasion not found with id: " + id);
        }
        occasionRepository.deleteById(id);
    }

    public List<Occasion> saveAll(List<Occasion> occasions) {
        return occasions.stream()
                .map(o -> saveOrUpdate(o.getId(), o))
                .toList();
    }

    private void validateCreateRequest(Occasion request) {

        if (request.getOccasionName() == null ||
                request.getOccasionCode() == null ||
                request.getDescription() == null ||
                request.getCategory() == null ||
                request.getIsActive() == null) {

            throw new IllegalArgumentException(
                    "All fields are required while creating a new Occasion");
        }
    }

    private void mapAllFields(Occasion target, Occasion source) {

        target.setOccasionName(source.getOccasionName());
        target.setOccasionCode(source.getOccasionCode());
        target.setDescription(source.getDescription());
        target.setCategory(source.getCategory());
        target.setIsActive(source.getIsActive());

        if (source.getS3ImageKey() != null) {
            target.setS3ImageKey(source.getS3ImageKey());
        }
    }

    private void mapNonNullFields(Occasion target, Occasion source) {

        if (source.getOccasionName() != null) {
            target.setOccasionName(source.getOccasionName());
        }

        if (source.getOccasionCode() != null) {
            target.setOccasionCode(source.getOccasionCode());
        }

        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }

        if (source.getCategory() != null) {
            target.setCategory(source.getCategory());
        }

        if (source.getIsActive() != null) {
            target.setIsActive(source.getIsActive());
        }

        if (source.getS3ImageKey() != null) {
            target.setS3ImageKey(source.getS3ImageKey());
        }
    }

}