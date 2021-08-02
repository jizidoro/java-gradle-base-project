package com.comrades.application.services.itinerary.queries;

import com.comrades.application.externals.BusLineExternal;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
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

    public Mono<ItineraryDto> getItineraryByLine(String lineName) throws URISyntaxException, IOException, InterruptedException, JSONException {
        var itinerary = BusLineExternal.getItineraryByLine(lineName);

        return Mono.just(itinerary);
    }

    public <T> Mono<T> monoResponseStatusNotFoundException() {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Itinerary not found"));
    }

}
