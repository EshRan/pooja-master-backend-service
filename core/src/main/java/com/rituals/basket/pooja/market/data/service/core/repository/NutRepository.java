package com.rituals.basket.pooja.market.data.service.core.repository;

import com.rituals.basket.pooja.market.data.service.core.model.Nut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutRepository extends JpaRepository<Nut, Long> {
}
