package com.comrades.application.services.airplane.commands;

import com.comrades.application.services.airplane.queries.AirplaneQuery;
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

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirplaneCommand {

    private final AirplaneRepository AirplaneRepository;
    private final AirplaneQuery AirplaneQuery;

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Airplane not found"));
    }

    public Mono<Airplane> save(Airplane Airplane) {
        return AirplaneRepository.save(Airplane);
    }

    @Transactional
    public Flux<Airplane> saveAll(List<Airplane> Airplanes) {
        return AirplaneRepository.saveAll(Airplanes)
                .doOnNext(this::throwResponseStatusExceptionWhenEmptyName);
    }

    private void throwResponseStatusExceptionWhenEmptyName(Airplane Airplane) {
        if (StringUtil.isNullOrEmpty(Airplane.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Name");
        }
    }

    public Mono<Void> update(Airplane Airplane) {
        return AirplaneRepository.findById(Airplane.getId())
                .flatMap(AirplaneRepository::save)
                .then();
    }

    public Mono<Void> delete(int id) {
        return AirplaneRepository.findById(id)
                .flatMap(AirplaneRepository::delete);
    }
}
