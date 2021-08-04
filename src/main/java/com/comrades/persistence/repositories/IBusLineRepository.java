package com.comrades.persistence.repositories;

import com.comrades.domain.models.BusLine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IBusLineRepository extends ReactiveCrudRepository<BusLine, Integer> {

    Mono<BusLine> findById(int id);
}
