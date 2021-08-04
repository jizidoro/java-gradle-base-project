package com.comrades.application.services.itinerary.queries;

import com.comrades.application.bases.MonoResponse;
import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.mappers.ItineraryMappingService;
import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.application.services.itinerary.IItineraryQuery;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.persistence.repositories.IItineraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItineraryQuery implements IItineraryQuery {

    private final BusLineExternal _busLineExternal;
    private final IItineraryRepository _itineraryRepository;
    private final ItineraryMappingService itineraryMappingService;

    public Flux<ItineraryDto> findAll() throws URISyntaxException, IOException, InterruptedException {
        var result = _itineraryRepository.findAll();

        return result.flatMap(itineraryMappingService::toItineraryDto);
    }

    public Flux<BusLineDto> findBusLineInRadius(double latitudeSelected, double longitudeSelected, double distanceSelected) throws URISyntaxException, IOException, InterruptedException {
        var busLines = _busLineExternal.findAllBusLine();
        List<ItineraryDto> itineraries = new ArrayList<>();
        List<Integer> busLineIds = new ArrayList<>();

        ParallelFlux<BusLineDto> searchItineraries = getBusLineDtoParallelFlux(busLines, itineraries);
        ParallelFlux<ItineraryDto> nearBusLines = getItineraryDtoParallelFlux(latitudeSelected, longitudeSelected, distanceSelected,
                itineraries.toArray(ItineraryDto[]::new), busLineIds);

        Flux.empty()
                .thenMany(searchItineraries)
                .thenMany(nearBusLines)
                .subscribe(new Consumer<ItineraryDto>() {
                    @Override
                    public void accept(ItineraryDto itineraryDto) {
                        log.info("done");
                    }
                });

        var result = Arrays.stream(busLines).filter(x -> busLineIds.contains(x.getId())).toArray(BusLineDto[]::new);

        return Flux.fromArray(result);
    }


    private ParallelFlux<BusLineDto> getBusLineDtoParallelFlux(BusLineDto[] busLines, List<ItineraryDto> itineraries) {
        return Flux.fromArray(busLines)
                .parallel(8)
                .runOn(Schedulers.parallel())
                .doOnNext(i -> {
                    itineraries.add(_busLineExternal.findItineraryByLine(i.getId()));
                });
    }


    private static ParallelFlux<ItineraryDto> getItineraryDtoParallelFlux(double latitudeSelected, double longitudeSelected, double distanceSelected, ItineraryDto[] itineraries, List<Integer> busLineIds) {
        final int R = 6371;

        return Flux.fromArray(itineraries)
                .parallel(8)
                .runOn(Schedulers.parallel())
                .doOnNext(i -> {
                    for (var coordinate : i.getCoordinates()) {
                        double latDistance = Math.toRadians(coordinate.getLat() - latitudeSelected);
                        double lonDistance = Math.toRadians(coordinate.getLng() - longitudeSelected);
                        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                                + Math.cos(Math.toRadians(latitudeSelected)) * Math.cos(Math.toRadians(coordinate.getLat()))
                                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                        double calculateDistance = R * c * 1000;

                        var result = Math.sqrt(calculateDistance);

                        if (result < distanceSelected) {
                            busLineIds.add(i.getIdlinha());
                        }
                    }
                });
    }

    public Flux<ItineraryDto> findItineraryByLineName(String lineName) throws URISyntaxException, IOException, InterruptedException, JSONException {
        var busLines = _busLineExternal.findAllBusLine();
        var selectedBusLines = Arrays.stream(busLines).filter(x -> lineName.equals(x.nome)).toArray(BusLineDto[]::new);
        List<ItineraryDto> itineraries = new ArrayList<>();

        Flux.fromArray(selectedBusLines)
                .parallel(8)
                .runOn(Schedulers.parallel())
                .doOnNext(i -> {
                    itineraries.add(_busLineExternal.findItineraryByLine(i.getId()));
                })
                .sequential()
                .blockLast();

        return Flux.fromArray(itineraries.toArray(ItineraryDto[]::new));
    }

    public Mono<ItineraryDto> findById(int id) {
        var result = _itineraryRepository.findById(id)
                .switchIfEmpty(MonoResponse.monoResponseStatusNotFoundException());
        return result.flatMap(itineraryMappingService::toItineraryDto);
    }


}
