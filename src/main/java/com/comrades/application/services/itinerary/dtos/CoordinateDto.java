package com.comrades.application.services.itinerary.dtos;

import com.comrades.domain.models.Coordinate;
import com.comrades.domain.models.Itinerary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoordinateDto {
    private Integer id;
    public int itineraryId;
    public double lat;
    public double lng;

    public CoordinateDto(Coordinate x) {
        itineraryId = x.getItineraryId();
        lat = x.getLatitude();
        lng = x.getLongitude();
    }
}

