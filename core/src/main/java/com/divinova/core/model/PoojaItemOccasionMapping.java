package com.divinova.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "pooja_item_occasion_mapping",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"pooja_item_id", "occasion_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PoojaItemOccasionMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pooja_item_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pooja_item_mapping_item"))
    private PoojaItem poojaItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "occasion_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pooja_item_mapping_occasion"))
    private Occasion occasion;

    @Column(columnDefinition = "TEXT")
    private String notes;

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

