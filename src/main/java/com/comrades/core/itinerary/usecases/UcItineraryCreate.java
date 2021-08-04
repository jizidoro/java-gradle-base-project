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
public class UcItineraryCreate extends UseCase<Itinerary> {

    @Autowired
    private IItineraryRepository _itineraryRepository;

    private Itinerary itinerary;

    public UcItineraryCreate(Itinerary itineraryInput) {
        super();
        itinerary = itineraryInput;
    }

    @Override
    protected Mono<Itinerary> execute() throws Exception {
        return _itineraryRepository.save(itinerary);
    }
}
