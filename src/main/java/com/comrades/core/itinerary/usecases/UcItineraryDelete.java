package com.comrades.core.itinerary.usecases;

import com.comrades.core.bases.UseCase;
import com.comrades.persistence.repositories.IItineraryRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@Getter
@Setter
public class UcItineraryDelete extends UseCase<Void> {

    @Autowired
    private IItineraryRepository _itineraryRepository;

    private int id;

    public UcItineraryDelete(int itineraryId) {
        super();
        id = itineraryId;
    }

    @Override
    protected Mono<Void> execute() throws Exception {
        var result = _itineraryRepository.findById(id)
                .flatMap(_itineraryRepository::delete);

        return result;
    }
}
