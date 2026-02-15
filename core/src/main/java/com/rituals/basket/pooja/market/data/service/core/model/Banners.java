package com.rituals.basket.pooja.market.data.service.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banners {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "item_name", length = 100, nullable = false)
        private String bannerName;

        @Column(name = "item_type", length = 50, nullable = false)
        private String bannerType;

        @Column(columnDefinition = "TEXT")
        private String description;

        @Column(name = "created_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE", insertable = false, updatable = false)
        private OffsetDateTime createdTsp;

        @Column(name = "updated_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE", insertable = false, updatable = false)
        private OffsetDateTime updatedTsp;

        @Column(name = "created_by", length = 100)
        private String createdBy;

        @Column(name = "updated_by", length = 100)
        private String updatedBy;
}
