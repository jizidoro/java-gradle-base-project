package com.comrades.application.services.airplane;

import com.comrades.application.services.airplane.dtos.AirplaneDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IAirplaneQuery {
    Flux<AirplaneDto> findAll() throws URISyntaxException, IOException, InterruptedException;

    Mono<AirplaneDto> findById(int id);
}
