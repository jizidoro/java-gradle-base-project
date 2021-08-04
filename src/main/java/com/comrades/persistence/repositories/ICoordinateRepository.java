package com.comrades.persistence.repositories;

import com.comrades.domain.models.Coordinate;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoordinateRepository extends ReactiveCrudRepository<Coordinate, Integer> {

    Mono<Coordinate> findById(int id);

    @Query("SELECT a.* FROM cord_coordinate a"
            + " JOIN itne_itinerary ba ON a.itne_sq_itinerary = ba.itne_sq_itinerary"
            + " WHERE ba.itne_sq_itinerary = :itineraryId"
            + " ORDER BY ba.itne_sq_itinerary")
    Flux<Coordinate> findByItinerary(long itineraryId);
}
