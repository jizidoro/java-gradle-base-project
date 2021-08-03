package com.comrades.application.services.busline.commands;

import com.comrades.application.services.busline.queries.BusLineQuery;
import com.comrades.domain.models.BusLine;
import com.comrades.persistence.repositories.BusLineRepository;
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
public class BusLineCommand {

    private final BusLineRepository BusLineRepository;

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "BusLine not found"));
    }

    public Mono<BusLine> save(BusLine BusLine) {
        return BusLineRepository.save(BusLine);
    }

    @Transactional
    public Flux<BusLine> saveAll(List<BusLine> BusLines) {
        return BusLineRepository.saveAll(BusLines)
                .doOnNext(this::throwResponseStatusExceptionWhenEmptyName);
    }

    private void throwResponseStatusExceptionWhenEmptyName(BusLine BusLine) {
        if (StringUtil.isNullOrEmpty(BusLine.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Name");
        }
    }

    public Mono<Void> update(BusLine BusLine) {
        return BusLineRepository.findById(BusLine.getId())
                .flatMap(BusLineRepository::save)
                .then();
    }

    public Mono<Void> delete(int id) {
        return BusLineRepository.findById(id)
                .flatMap(BusLineRepository::delete);
    }
}
