package com.comrades.core.itinerary.usecases;

import com.comrades.core.bases.UseCase;
import com.comrades.domain.models.Itinerary;
import com.comrades.persistence.repositories.IItineraryRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@Getter
@Setter
public class UcItineraryEdit extends UseCase<Void> {

    @Autowired
    private IItineraryRepository _itineraryRepository;

    private Itinerary itinerary;

    public UcItineraryEdit(Itinerary itineraryInput) {
        super();
        itinerary = itineraryInput;
    }

    @Override
    protected Mono<Void> execute() throws Exception {
        return _itineraryRepository.findById(itinerary.getId())
                .flatMap(_itineraryRepository::save)
                .then();
    }
}
