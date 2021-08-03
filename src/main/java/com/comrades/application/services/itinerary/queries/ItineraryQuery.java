package com.comrades.application.services.itinerary.queries;

import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.services.airplane.dtos.AirplaneDto;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.domain.models.Itinerary;
import com.comrades.persistence.repositories.ItineraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItineraryQuery {

    private final BusLineExternal BusLineExternal;
    private final ItineraryRepository ItineraryRepository;

    public Flux<ItineraryDto> findAll() throws URISyntaxException, IOException, InterruptedException {
        var result = ItineraryRepository.findAll();

        return result.map(x -> new ItineraryDto(x));
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        return Math.sqrt(distance);
    }

    public Mono<ItineraryDto> findItineraryByLine(String lineName) throws URISyntaxException, IOException, InterruptedException, JSONException {
        var itinerary = BusLineExternal.getItineraryByLine(lineName);

        return Mono.just(itinerary);
    }

    public Mono<ItineraryDto> findById(int id) {
        var result = ItineraryRepository.findById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException());
        return result.map(x -> new ItineraryDto(x));
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Itinerary not found"));
    }

}
