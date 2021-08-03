package com.comrades.application.mappers;

import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.domain.models.Itinerary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItineraryMapper {

    ItineraryMapper INSTANCE = Mappers.getMapper(ItineraryMapper.class);

    ItineraryDto itineraryToItineraryDto(Itinerary itinerary);

    Itinerary itineraryDtoToItinerary(ItineraryDto itineraryDto);
}
