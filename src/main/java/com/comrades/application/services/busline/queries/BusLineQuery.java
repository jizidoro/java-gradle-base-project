package com.comrades.application.services.busline.queries;

import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.persistence.repositories.BusLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusLineQuery {

    private final BusLineExternal BusLineExternal;
    private final BusLineRepository BusLineRepository;

    public Flux<BusLineDto> findAllBusLinesJson() throws URISyntaxException, IOException, InterruptedException {
        var busLines = BusLineExternal.findAllBusLine();
        return Flux.fromArray(busLines);
    }

    public Flux<BusLineDto> findAll() throws URISyntaxException, IOException, InterruptedException {
        var result = BusLineRepository.findAll();
        return result.map(x -> new BusLineDto(x));
    }


    public Flux<BusLineDto> findBusLineByName(String busLineName) throws URISyntaxException, IOException, InterruptedException {
        var busLines = BusLineExternal.findAllBusLine();
        var result = Arrays.stream(busLines).filter(c -> busLineName.equals(c.getNome())).toArray(BusLineDto[]::new);
        return Flux.fromArray(result);
    }

    public Mono<BusLineDto> findById(int id) {
        var result = BusLineRepository.findById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException());
        return result.map(x -> new BusLineDto(x));
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "BusLine not found"));
    }

}
