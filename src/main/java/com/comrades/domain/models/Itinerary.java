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
@Table("itne_itinerary")
public class Itinerary {

    @Id
    @With
    @Column("itne_sq_itinerary")
    private Integer id;

    @Column("busi_sq_bus_line")
    private Integer busLineId;

    @NotNull
    @Column("itne_tx_name")
    private String nome;

    @NotNull
    @Column("itne_tx_code")
    private String codigo;
}
