package com.rituals.basket.pooja.market.data.service.core.repository;

import com.rituals.basket.pooja.market.data.service.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsInStockTrue();
}
