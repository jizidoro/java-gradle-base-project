package com.comrades.application.services.itinerary.commands;

import com.comrades.application.mappers.ItineraryMapper;
import com.comrades.application.services.itinerary.IItineraryCommand;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.core.bases.UseCaseFacade;
import com.comrades.core.itinerary.usecases.UcItineraryCreate;
import com.comrades.core.itinerary.usecases.UcItineraryDelete;
import com.comrades.core.itinerary.usecases.UcItineraryEdit;
import com.comrades.domain.models.Itinerary;
import com.comrades.persistence.repositories.IItineraryRepository;
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
public class ItineraryCommand implements IItineraryCommand {

    private final IItineraryRepository _itineraryRepository;
    private final UseCaseFacade facade;

    public Mono<Itinerary> save(ItineraryDto itinerary) {
        var result = ItineraryMapper.INSTANCE.toItinerary(itinerary);
        var uc = new UcItineraryCreate(result);
        return facade.execute(uc);
    }

    @Transactional
    public Flux<Itinerary> saveAll(List<Itinerary> itineraries) {
        return _itineraryRepository.saveAll(itineraries)
                .doOnNext(this::throwResponseStatusExceptionWhenEmptyName);
    }

    private void throwResponseStatusExceptionWhenEmptyName(Itinerary Itinerary) {
        if (StringUtil.isNullOrEmpty(Itinerary.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Name");
        }
    }

    public Mono<Void> update(ItineraryDto itinerary) {
        var result = ItineraryMapper.INSTANCE.toItinerary(itinerary);
        var uc = new UcItineraryEdit(result);
        return facade.execute(uc).then();
    }

    public Mono<Void> delete(int id) {
        var uc = new UcItineraryDelete(id);
        return facade.execute(uc).then();
    }
}
