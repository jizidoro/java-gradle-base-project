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
    public int id;
    public int itineraryId;
    public String lat;
    public String lng;

    public CoordinateDto(Coordinate x) {
        id = x.getId();
        id = x.getItineraryId();
        lat = x.getLatitude();
        lng = x.getLongitude();
    }
}

