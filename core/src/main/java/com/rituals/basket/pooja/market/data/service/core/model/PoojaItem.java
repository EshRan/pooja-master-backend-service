package com.rituals.basket.pooja.market.data.service.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "pooja_items", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "item_code" })
})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class PoojaItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "item_code", length = 50, nullable = false, unique = true)
        private String itemCode;

        @Column(name = "item_name", length = 100, nullable = false)
        private String itemName;

        @Column(columnDefinition = "TEXT")
        private String description;

        @Column(name = "image_url", length = 500)
        private String imageUrl;

        @Column(name = "video_url", length = 500)
        private String videoUrl;

        @Column(name = "poster_url", length = 500)
        private String posterUrl;

        @Column(name = "price", precision = 10, scale = 2)
        private BigDecimal price;

        @Column(name = "s3_image_key", length = 500)
        private String s3ImageKey;

        @Column(name = "quantity_unit", length = 50)
        private String quantityUnit;

        @Column(name = "estimated_quantity", precision = 10, scale = 2)
        private BigDecimal estimatedQuantity;

        @Column(name = "is_active", nullable = false)
        private Boolean isActive = true;

        @Column(name = "created_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
        private OffsetDateTime createdTsp;

        @Column(name = "updated_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
        private OffsetDateTime updatedTsp;

        @Column(name = "created_by", length = 100)
        private String createdBy;

        @Column(name = "updated_by", length = 100)
        private String updatedBy;
}
