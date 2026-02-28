package com.rituals.basket.pooja.market.data.service.core.repository;

import com.rituals.basket.pooja.market.data.service.core.model.PoojaItemOccasionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoojaItemOccasionMappingRepository extends JpaRepository<PoojaItemOccasionMapping, Long> {

    List<PoojaItemOccasionMapping> findByOccasionId(Long occasionId);

}
