package com.comrades.application.services.busline;

import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.domain.models.BusLine;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBusLineCommand {
    Mono<BusLine> save(BusLineDto busLine);

    Flux<BusLine> saveAll(List<BusLine> busLines);

    Mono<Void> update(BusLineDto busLine);

    Mono<Void> delete(int id);
}
