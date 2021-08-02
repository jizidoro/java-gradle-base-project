package com.comrades.api.usecases.v1;

import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.application.services.itinerary.queries.ItineraryQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("Itinerarys")
@Slf4j
@RequiredArgsConstructor
@SecurityScheme(
        name = "Basic Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class ItineraryController {

    private final ItineraryQuery ItineraryQuery;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all Itinerarys by line",
            tags = {"Itinerary"})
    public Mono<ItineraryDto> getItineraryByLine(String lineName) {
        try {
            return ItineraryQuery.getItineraryByLine(lineName);
        } catch (Exception ex) {
            return Mono.empty();
        }

    }

}
