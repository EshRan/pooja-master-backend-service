package com.rituals.basket.pooja.market.data.service.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String s3ImageKey;
    private String imageUrl;
    
    // Measurement unit, e.g., 'gm', 'kg', 'piece'
    private String quantityUnit;
    
    // Is the item currently in stock?
    private Boolean isInStock;

    // Available quantity logic
    // How many units of this item are available in stock
    private Integer stockInQuantity;
    
    // Default or total quantity per item purchase package. (e.g 100 representing 100gms)
    private Integer totalQuantity;
}
