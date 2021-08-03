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
@Table("itne_itinerary")
public class Itinerary {

    @Id
    @Column("itne_sq_itinerary")
    private Integer id;

    @NotNull
    @Column("itne_tx_name")
    private String nome;

    @NotNull
    @Column("itne_tx_code")
    private String codigo;
}