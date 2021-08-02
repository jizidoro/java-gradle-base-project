package com.comrades.persistence.repositories;

import com.comrades.domain.models.Airplane;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AirplaneRepository extends ReactiveCrudRepository<Airplane, Integer> {

    Mono<Airplane> findById(int id);
}
