package com.divinova.pooja.market.data.service.core.repository;


import com.divinova.pooja.market.data.service.core.model.PoojaItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PoojaItemRepository extends JpaRepository<PoojaItem, Long> {
}
