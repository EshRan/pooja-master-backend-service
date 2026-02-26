package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.model.Occasion;
import com.rituals.basket.pooja.market.data.service.core.repository.OccasionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OccasionService {

    private static final String BASE_URL =
            "https://rituals-basket.s3.ap-south-1.amazonaws.com/";

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
                .orElseThrow(() ->
                        new RuntimeException("Occasion not found with id: " + id));

        mapNonNullFields(existing, request);

        return occasionRepository.save(existing);
    }

    // =========================================================
    // GET METHODS
    // =========================================================

    public Occasion get(Long id) {
        Occasion occasion = occasionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Occasion not found: " + id));

        enrichImageUrl(occasion);
        return occasion;
    }

    public List<Occasion> getAll() {
        List<Occasion> occasions = occasionRepository.findAll();
        occasions.forEach(this::enrichImageUrl);
        return occasions;
    }

    public List<Occasion> getByCategory(String category) {
        List<Occasion> occasions = occasionRepository.findByCategory(category);
        occasions.forEach(this::enrichImageUrl);
        return occasions;
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
            target.setS3ImageKey(buildS3Url(source.getS3ImageKey()));
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
            target.setS3ImageKey(buildS3Url(source.getS3ImageKey()));
        }
    }


    private void enrichImageUrl(Occasion occasion) {
        if (occasion.getS3ImageKey() != null &&
                !occasion.getS3ImageKey().startsWith(BASE_URL)) {

            occasion.setS3ImageKey(buildS3Url(occasion.getS3ImageKey()));
        }
    }

    private String buildS3Url(String key) {

        if (key == null) return null;

        if (key.startsWith("http")) {
            return key;
        }

        return BASE_URL + key;
    }
}