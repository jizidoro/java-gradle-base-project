package com.comrades.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("cord_coordinate")
public class Coordinate {

    @Id
    @Column("cord_sq_coordinate")
    private Integer id;

    @Column("itne_sq_itinerary")
    private Integer itineraryId;

    @NotNull
    @Column("cord_dp_longitude")
    private Double longitude;

    @NotNull
    @Column("cord_dp_latitude")
    private Double latitude;
}
