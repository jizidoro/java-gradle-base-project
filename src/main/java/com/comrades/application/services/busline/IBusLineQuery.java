package com.comrades.application.services.busline;

import com.comrades.application.services.busline.dtos.BusLineDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IBusLineQuery {
    Flux<BusLineDto> findAll() throws URISyntaxException, IOException, InterruptedException;

    Mono<BusLineDto> findById(int id);

    Flux<BusLineDto> findBusLineByName(String busLineName) throws URISyntaxException, IOException, InterruptedException;

    Flux<BusLineDto> findAllBusLinesJson() throws URISyntaxException, IOException, InterruptedException;
}
