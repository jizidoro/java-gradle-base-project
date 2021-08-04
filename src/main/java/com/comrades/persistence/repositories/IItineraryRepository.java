package com.comrades.persistence.repositories;

import com.comrades.domain.models.Itinerary;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IItineraryRepository extends ReactiveCrudRepository<Itinerary, Integer> {

    Mono<Itinerary> findById(int id);
}
