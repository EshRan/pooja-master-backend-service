package com.divinova.pooja.market.data.service.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "occasions",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"occasion_code"})
        }
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(
            name = "created_tsp",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false,
            updatable = false
    )
    private OffsetDateTime createdTsp;

    @Column(
            name = "updated_tsp",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false,
            updatable = false
    )
    private OffsetDateTime updatedTsp;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;
}

