package com.comrades.application.services.airplane.queries;

import com.comrades.application.mappers.AirplaneMapper;
import com.comrades.application.services.airplane.dtos.AirplaneDto;
import com.comrades.domain.models.Airplane;
import com.comrades.persistence.repositories.AirplaneRepository;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirplaneQuery {

    private final AirplaneRepository AirplaneRepository;

    public Flux<AirplaneDto> findAll() throws URISyntaxException, IOException, InterruptedException {
        var result = AirplaneRepository.findAll();
        return result.map(x -> AirplaneMapper.INSTANCE.airplaneToAirplaneDto(x));
    }

    public Mono<AirplaneDto> findById(int id) {
        var result = AirplaneRepository.findById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException());
        return result.map(x -> AirplaneMapper.INSTANCE.airplaneToAirplaneDto(x));
    }


    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Airplane not found"));
    }

}
