package com.comrades.core.itinerary.usecases;

import com.comrades.core.bases.UseCase;
import com.comrades.domain.models.Coordinate;
import com.comrades.domain.models.Itinerary;
import com.comrades.persistence.repositories.ICoordinateRepository;
import com.comrades.persistence.repositories.IItineraryRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

@Getter
@Setter
public class UcItineraryCreate extends UseCase<Itinerary> {

    @Autowired
    private IItineraryRepository _itineraryRepository;

    @Autowired
    private ICoordinateRepository _coordinateRepository;

    private Itinerary itinerary;
    private List<Coordinate> coordinates;

    public UcItineraryCreate(Itinerary itineraryInput, List<Coordinate> coordinatesInput) {
        super();
        itinerary = itineraryInput;
        coordinates = coordinatesInput;
    }

    @Override
    protected Mono<Itinerary> execute() throws Exception {
        return _itineraryRepository.save(itinerary).doOnNext(i -> {
            coordinates.forEach(x -> x.setItineraryId(i.getId()));
            _coordinateRepository.saveAll(coordinates).subscribe(new Consumer<Coordinate>() {
                @Override
                public void accept(Coordinate coordinate) {
                    System.out.println("Seu texto é inserido aqui, entre aspas triplas");
                }
            });
        });
    }
}
