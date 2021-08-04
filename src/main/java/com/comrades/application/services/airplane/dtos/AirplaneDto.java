package com.comrades.application.services.airplane.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AirplaneDto {

    private Integer id;

    @NotNull
    @NotEmpty
    private String codigo;

    @NotNull
    @NotEmpty
    private String modelo;

    @NotNull
    @NotEmpty
    private Integer quantidadePassageiro;

    private String dataRegistro;

}
