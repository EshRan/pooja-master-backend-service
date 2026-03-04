package com.rituals.basket.pooja.market.data.service.core.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "occasions", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "occasion_code" })
})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Occasion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "occasion_code", length = 50, nullable = false, unique = true)
    private String occasionCode;

    @Column(name = "occasion_name", length = 100, nullable = false)
    private String occasionName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "s3_image_name", length = 500)
    private String s3ImageKey;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "occasion_date")
    private LocalDate occasionDate;

    @Column(name = "created_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE", insertable = false, updatable = false)
    private OffsetDateTime createdTsp;

    @Column(name = "updated_tsp", columnDefinition = "TIMESTAMP WITH TIME ZONE", insertable = false, updatable = false)
    private OffsetDateTime updatedTsp;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;
}
