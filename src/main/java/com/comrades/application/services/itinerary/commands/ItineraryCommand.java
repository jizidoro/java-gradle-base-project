package com.comrades.application.services.itinerary.commands;

import com.comrades.domain.models.Itinerary;
import com.comrades.persistence.repositories.ItineraryRepository;
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
public class ItineraryCommand {

    private final ItineraryRepository ItineraryRepository;

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Itinerary not found"));
    }

    public Mono<Itinerary> save(Itinerary Itinerary) {
        return ItineraryRepository.save(Itinerary);
    }

    @Transactional
    public Flux<Itinerary> saveAll(List<Itinerary> itineraries) {
        return ItineraryRepository.saveAll(itineraries)
                .doOnNext(this::throwResponseStatusExceptionWhenEmptyName);
    }

    private void throwResponseStatusExceptionWhenEmptyName(Itinerary Itinerary) {
        if (StringUtil.isNullOrEmpty(Itinerary.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Name");
        }
    }

    public Mono<Void> update(Itinerary Itinerary) {
        return ItineraryRepository.findById(Itinerary.getId())
                .flatMap(ItineraryRepository::save)
                .then();
    }

    public Mono<Void> delete(int id) {
        return ItineraryRepository.findById(id)
                .flatMap(ItineraryRepository::delete);
    }
}
