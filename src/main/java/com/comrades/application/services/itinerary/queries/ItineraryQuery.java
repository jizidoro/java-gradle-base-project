package com.comrades.application.services.itinerary.queries;

import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.application.services.itinerary.dtos.CoordinateDto;
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
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

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

    public Flux<BusLineDto> findBusLineInRadius(double latitudeSelected, double longitudeSelected, double distanceSelected) throws URISyntaxException, IOException, InterruptedException {
        var busLines = BusLineExternal.findAllBusLine();
        List<ItineraryDto> itineraries = new ArrayList<>();

        Flux.fromArray(busLines)
                .parallel(8)
                .runOn(Schedulers.parallel())
                .doOnNext(i -> {
                    System.out.println("primeira parte" + i.getId());
                    itineraries.add(BusLineExternal.findItineraryByLine(i.getId()));
                })
                .sequential()
                .blockLast();

        var busLineIds = distance(latitudeSelected, longitudeSelected, distanceSelected, itineraries.toArray(ItineraryDto[]::new));

        var result = Arrays.stream(busLines).filter(x -> busLineIds.contains(x.getId())).toArray(BusLineDto[]::new);

        return Flux.fromArray(result);
    }


    public static List<Integer> distance(double latitudeSelected, double longitudeSelected, double distanceSelected, ItineraryDto[] itineraries) {

        final int R = 6371;

        List<Integer> busLineIds = new ArrayList<>();

        Flux.fromArray(itineraries)
                .parallel(8)
                .runOn(Schedulers.parallel())
                .doOnNext(i -> {
                    System.out.println("segunda parte" + i.getIdlinha());
                    for (var coordenada : i.getCoordinatesDto()) {
                        double latDistance = Math.toRadians(coordenada.getLat() - latitudeSelected);
                        double lonDistance = Math.toRadians(coordenada.getLng() - longitudeSelected);
                        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                                + Math.cos(Math.toRadians(latitudeSelected)) * Math.cos(Math.toRadians(coordenada.getLat()))
                                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                        double calculateDistance = R * c * 1000;

                        var result = Math.sqrt(calculateDistance);

                        if (result < distanceSelected) {
                            busLineIds.add(i.getIdlinha());
                        }
                    }

                })
                .sequential()
                .blockLast();

        return busLineIds;
    }

    public Flux<ItineraryDto> findItineraryByLineName(String lineName) throws URISyntaxException, IOException, InterruptedException, JSONException {
        var busLines = BusLineExternal.findAllBusLine();
        var selectedBusLines = Arrays.stream(busLines).filter(x -> lineName.equals(x.nome)).toArray(BusLineDto[]::new);

        List<ItineraryDto> itineraries = new ArrayList<>();

        Flux.fromArray(selectedBusLines).flatMap(x -> {
            itineraries.add(BusLineExternal.findItineraryByLine(x.getId()));
            return null;
        });

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
