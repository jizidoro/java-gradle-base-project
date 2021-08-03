package com.comrades.domain.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("cord_sq_coordinate")
public class Coordinate {

    @Id
    @Column("cord_sq_coordinate")
    private Integer id;

    @Column("itne_sq_itinerary")
    private Integer itineraryId;

    @NotNull
    @Column("cord_tx_longitude")
    private double longitude;

    @NotNull
    @Column("cord_tx_latitude")
    private double latitude;



}
