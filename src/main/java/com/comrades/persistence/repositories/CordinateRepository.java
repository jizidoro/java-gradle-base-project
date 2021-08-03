package com.comrades.persistence.repositories;

import com.comrades.domain.models.Cordinate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CordinateRepository extends ReactiveCrudRepository<Cordinate, Integer> {

    Mono<Cordinate> findById(int id);
}
