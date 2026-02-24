package com.rituals.basket.pooja.market.data.service.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "nuts", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "item_code" })
})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class Nut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_code", length = 50, nullable = false, unique = true)
    private String itemCode;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "total_quantity", precision = 10, scale = 2)
    private BigDecimal totalQuantity;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "s3_image_name", length = 500)
    private String s3ImageKey;

    @Column(name = "quantity_unit", length = 50)
    private String quantityUnit;

    @Column(name = "in_stock", nullable = false)
    private Boolean isInStock = true;

    @Column(name = "created_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdTsp;

    @Column(name = "updated_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedTsp;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;
}
