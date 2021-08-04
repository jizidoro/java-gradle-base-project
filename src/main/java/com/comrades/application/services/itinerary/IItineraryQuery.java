package com.comrades.application.services.itinerary;

import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import org.springframework.boot.configurationprocessor.json.JSONException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IItineraryQuery {
    Flux<ItineraryDto> findAll() throws URISyntaxException, IOException, InterruptedException;

    Mono<ItineraryDto> findById(int id);

    Flux<BusLineDto> findBusLineInRadius(double latitudeSelected, double longitudeSelected, double distanceSelected) throws URISyntaxException, IOException, InterruptedException;

    Flux<ItineraryDto> findItineraryByLineName(String lineName) throws URISyntaxException, IOException, InterruptedException, JSONException;
}
