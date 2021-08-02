package com.comrades.application.services.busline.queries;

import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.services.busline.dtos.BusLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusLineQuery {

    private final BusLineExternal BusLineExternal;

    public Flux<BusLineDto> getAllBusLines() throws URISyntaxException, IOException, InterruptedException {
        var busLines = BusLineExternal.getAllBusLines();

        return Flux.fromArray(busLines);
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "BusLine not found"));
    }

}
