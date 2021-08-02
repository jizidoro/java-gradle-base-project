package com.comrades.application.services.itinerary.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItineraryDto {
    public String idlinha;
    public String nome;
    public String codigo;
    List<CoordinateDto> coordinatesDto;
}
