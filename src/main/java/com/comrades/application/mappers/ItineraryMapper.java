package com.comrades.application.mappers;

import com.comrades.application.services.itinerary.dtos.CoordinateDto;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.domain.models.Coordinate;
import com.comrades.domain.models.Itinerary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItineraryMapper {

    ItineraryMapper INSTANCE = Mappers.getMapper(ItineraryMapper.class);

    @Mapping(target = "coordinates", ignore = true)
    @Mapping(target = "idlinha", source = "busLineId")
    ItineraryDto toItineraryDto(Itinerary itinerary);

    @Mapping(target = "busLineId", source = "idlinha")
    Itinerary toItinerary(ItineraryDto itineraryDto);

    List<CoordinateDto> toCoordinateDtos(List<Coordinate> coordinate);
}
