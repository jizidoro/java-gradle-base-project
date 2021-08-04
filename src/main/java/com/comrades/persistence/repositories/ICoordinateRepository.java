package com.comrades.persistence.repositories;

import com.comrades.domain.models.Coordinate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ICoordinateRepository extends ReactiveCrudRepository<Coordinate, Integer> {

    Mono<Coordinate> findById(int id);
}
