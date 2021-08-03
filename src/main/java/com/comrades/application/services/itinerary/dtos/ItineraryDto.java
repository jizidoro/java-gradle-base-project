package com.comrades.application.services.itinerary.dtos;

import com.comrades.domain.models.Itinerary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItineraryDto {
    private Integer id;
    public int idlinha;
    public String nome;
    public String codigo;
    List<CoordinateDto> coordinatesDto;

}
