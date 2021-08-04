package com.comrades.application.services.itinerary.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItineraryDto {
    private Integer id;

    @NotNull
    public Integer idlinha;

    @NotNull
    @NotEmpty
    public String nome;

    @NotNull
    @NotEmpty
    public String codigo;

    List<CoordinateDto> coordinatesDto;

}
