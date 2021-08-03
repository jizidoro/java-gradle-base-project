package com.comrades.api.usecases.v1;

import com.comrades.application.services.itinerary.commands.ItineraryCommand;
import com.comrades.application.services.itinerary.dtos.ItineraryDto;
import com.comrades.application.services.itinerary.queries.ItineraryQuery;
import com.comrades.domain.models.Itinerary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("itineraries")
@Slf4j
@RequiredArgsConstructor
@SecurityScheme(
        name = "Basic Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class ItineraryController {

    private final ItineraryQuery ItineraryQuery;
    private final ItineraryCommand ItineraryCommand;

    @GetMapping(path = "listAll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all itineraries",
            tags = {"Itinerary"})
    public Flux<ItineraryDto> listAll() {
        try {
            return ItineraryQuery.findAll();
        } catch (Exception ex) {
            return Flux.empty();
        }
    }

    @GetMapping(path = "findItineraryByLine")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all itineraries by line",
            tags = {"Itinerary"})
    public Mono<ItineraryDto> findItineraryByLine(String busLineName) {
        try {
            return ItineraryQuery.findItineraryByLine(busLineName);
        } catch (Exception ex) {
            return Mono.empty();
        }
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            tags = {"Itinerary"})
    public Mono<ItineraryDto> findById(@PathVariable int id) {
        return ItineraryQuery.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"Itinerary"})
    public Mono<Itinerary> save(@Valid @RequestBody Itinerary Itinerary) {
        return ItineraryCommand.save(Itinerary);
    }

    @PostMapping("batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"Itinerary"})
    public Flux<Itinerary> saveBatch(@RequestBody List<Itinerary> itineraries) {
        return ItineraryCommand.saveAll(itineraries);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"Itinerary"})
    public Mono<Void> update(@PathVariable int id, @Valid @RequestBody Itinerary Itinerary) {
        return ItineraryCommand.update(Itinerary.withId(id));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"Itinerary"})
    public Mono<Void> delete(@PathVariable int id) {
        return ItineraryCommand.delete(id);
    }

}
