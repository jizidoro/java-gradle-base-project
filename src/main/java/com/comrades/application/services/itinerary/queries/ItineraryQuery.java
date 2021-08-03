package com.comrades.application.services.itinerary.queries;

import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
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
import java.util.ArrayList;
import java.util.Arrays;
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

    public Flux<BusLineDto> findBusLineInRadius() throws URISyntaxException, IOException, InterruptedException {


        var busLines = BusLineExternal.findAllBusLine();
        for (var busline : busLines) {
            var result = BusLineExternal.findItineraryByLine(busline.id);
        }


        return Flux.empty();
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

    public Flux<ItineraryDto> findItineraryByLineName(String lineName) throws URISyntaxException, IOException, InterruptedException, JSONException {
        var busLines = BusLineExternal.findAllBusLine();
        var selectedBusLines = Arrays.stream(busLines).filter(x -> lineName.equals(x.nome)).toArray(BusLineDto[]::new);

        List<ItineraryDto> itineraries = new ArrayList<>();
        for (var busline : selectedBusLines) {
            itineraries.add(BusLineExternal.findItineraryByLine(busline.getId()));
        }

        return Flux.fromArray(itineraries.toArray(ItineraryDto[]::new));
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
