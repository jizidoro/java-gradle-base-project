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
@Table("cord_sq_cordinate")
public class Cordinate {

    @Id
    @Column("itne_sq_itinerary")
    private Integer id;

    @NotNull
    @Column("cord_tx_longitude")
    private String longitude;

    @NotNull
    @Column("cord_tx_latitude")
    private String latitude;

}
