package com.comrades.application.services.airplane;

import com.comrades.application.services.airplane.dtos.AirplaneDto;
import com.comrades.domain.models.Airplane;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IAirplaneCommand {
    Mono<Airplane> save(AirplaneDto airplane);

    Flux<Airplane> saveAll(List<Airplane> airplanes);

    Mono<Void> update(AirplaneDto airplane);

    Mono<Void> delete(int id);
}
