package com.rituals.basket.pooja.market.data.service.core.repository;


import com.rituals.basket.pooja.market.data.service.core.model.PoojaItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PoojaItemRepository extends JpaRepository<PoojaItem, Long> {
}
