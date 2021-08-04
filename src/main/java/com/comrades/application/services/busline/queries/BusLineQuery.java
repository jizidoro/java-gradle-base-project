package com.comrades.application.services.busline.queries;

import com.comrades.application.bases.MonoResponse;
import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.mappers.BusLineMapper;
import com.comrades.application.services.busline.IBusLineQuery;
import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.persistence.repositories.IBusLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusLineQuery implements IBusLineQuery {

    private final BusLineExternal _busLineExternal;
    private final IBusLineRepository _busLineRepository;

    public Flux<BusLineDto> findAllBusLinesJson() throws URISyntaxException, IOException, InterruptedException {
        var busLines = _busLineExternal.findAllBusLine();
        return Flux.fromArray(busLines);
    }

    public Flux<BusLineDto> findAll() throws URISyntaxException, IOException, InterruptedException {
        var result = _busLineRepository.findAll();
        return result.map(x -> BusLineMapper.INSTANCE.toBusLineDto(x));
    }

    public Flux<BusLineDto> findBusLineByName(String busLineName) throws URISyntaxException, IOException, InterruptedException {
        var busLines = _busLineExternal.findAllBusLine();
        var result = Arrays.stream(busLines).filter(c -> busLineName.equals(c.getNome())).toArray(BusLineDto[]::new);
        return Flux.fromArray(result);
    }

    public Mono<BusLineDto> findById(int id) {
        var result = _busLineRepository.findById(id)
                .switchIfEmpty(MonoResponse.monoResponseStatusNotFoundException());
        return result.map(x -> BusLineMapper.INSTANCE.toBusLineDto(x));
    }


}
