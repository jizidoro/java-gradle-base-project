package com.comrades.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("cord_coordinate")
public class Coordinate {

    @Id
    @With
    @Column("cord_sq_coordinate")
    private Integer id;

    @Column("itne_sq_itinerary")
    private Integer itineraryId;

    @NotNull
    @Column("cord_dp_longitude")
    private Double lng;

    @NotNull
    @Column("cord_dp_latitude")
    private Double lat;
}
