package com.comrades.application.services.airplane.queries;

import com.comrades.application.bases.MonoResponse;
import com.comrades.application.mappers.AirplaneMapper;
import com.comrades.application.services.airplane.IAirplaneQuery;
import com.comrades.application.services.airplane.dtos.AirplaneDto;
import com.comrades.persistence.repositories.IAirplaneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirplaneQuery implements IAirplaneQuery {

    private final IAirplaneRepository _airplaneRepository;

    public Flux<AirplaneDto> findAll() throws URISyntaxException, IOException, InterruptedException {
        var result = _airplaneRepository.findAll();
        return result.map(x -> AirplaneMapper.INSTANCE.toAirplaneDto(x));
    }

    public Mono<AirplaneDto> findById(int id) {
        var result = _airplaneRepository.findById(id)
                .switchIfEmpty(MonoResponse.monoResponseStatusNotFoundException());
        return result.map(x -> AirplaneMapper.INSTANCE.toAirplaneDto(x));
    }


}
