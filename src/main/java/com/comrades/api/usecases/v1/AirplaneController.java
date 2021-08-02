package com.comrades.api.usecases.v1;


import com.comrades.application.services.airplane.commands.AirplaneCommand;
import com.comrades.application.services.airplane.queries.AirplaneQuery;
import com.comrades.domain.models.Airplane;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("Airplanes")
@Slf4j
@RequiredArgsConstructor
@SecurityScheme(
        name = "Basic Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class AirplaneController {

    private final AirplaneCommand AirplaneCommand;
    private final AirplaneQuery AirplaneQuery;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all Airplanes",
            tags = {"Airplane"})
    public Flux<Airplane> listAll() {
        return AirplaneQuery.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            tags = {"Airplane"})
    public Mono<Airplane> findById(@PathVariable int id) {
        return AirplaneQuery.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"Airplane"})
    public Mono<Airplane> save(@Valid @RequestBody Airplane Airplane) {
        return AirplaneCommand.save(Airplane);
    }

    @PostMapping("batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"Airplane"})
    public Flux<Airplane> saveBatch(@RequestBody List<Airplane> Airplanes) {
        return AirplaneCommand.saveAll(Airplanes);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"Airplane"})
    public Mono<Void> update(@PathVariable int id, @Valid @RequestBody Airplane Airplane) {
        return AirplaneCommand.update(Airplane.withId(id));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"Airplane"})
    public Mono<Void> delete(@PathVariable int id) {
        return AirplaneCommand.delete(id);
    }

}
