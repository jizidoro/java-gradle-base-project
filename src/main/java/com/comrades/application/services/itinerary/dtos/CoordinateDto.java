package com.comrades.application.services.itinerary.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoordinateDto {
    private Integer id;
    public Integer itineraryId;
    public Double lat;
    public Double lng;

}

