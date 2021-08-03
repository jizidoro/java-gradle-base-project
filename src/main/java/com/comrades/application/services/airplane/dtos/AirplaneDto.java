package com.comrades.application.services.airplane.dtos;

import com.comrades.domain.models.Airplane;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AirplaneDto {

    private Integer id;

    private String name;

    private String modelo;

    private Integer quantidadePassageiro;

    private String dataRegistro;

}
