package com.rituals.basket.pooja.market.data.service.core.repository;

import com.rituals.basket.pooja.market.data.service.core.model.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OccasionRepository extends JpaRepository<Occasion, Long> {

    List<Occasion> findByCategory(String category);

    List<Occasion> findByIsActive(Boolean isActive);

    List<Occasion> findByCategoryAndIsActive(String category, Boolean isActive);

}
