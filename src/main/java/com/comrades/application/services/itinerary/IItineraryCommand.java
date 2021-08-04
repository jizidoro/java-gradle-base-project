package com.comrades.application.services.itinerary;

import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.domain.models.Itinerary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IItineraryCommand {
    Mono<Itinerary> save(ItineraryDto itinerary);

    Flux<Itinerary> saveAll(List<Itinerary> itinerarys);

    Mono<Void> update(ItineraryDto itinerary);

    Mono<Void> delete(int id);
}
