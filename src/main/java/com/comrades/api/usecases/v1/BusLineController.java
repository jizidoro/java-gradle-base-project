package com.comrades.api.usecases.v1;


import com.comrades.application.services.busline.commands.BusLineCommand;
import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.application.services.busline.queries.BusLineQuery;
import com.comrades.domain.models.BusLine;
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
@RequestMapping("BusLines")
@Slf4j
@RequiredArgsConstructor
@SecurityScheme(
        name = "Basic Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class BusLineController {

    private final BusLineQuery BusLineQuery;
    private final BusLineCommand BusLineCommand;

    @GetMapping(path = "listAll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all BusLines DB",
            tags = {"BusLine"})
    public Flux<BusLineDto> listAll() {
        try {
            return BusLineQuery.findAll();
        } catch (Exception ex) {
            return Flux.empty();
        }
    }

    @GetMapping(path = "listAllJson")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all BusLines form json",
            tags = {"BusLine"})
    public Flux<BusLineDto> listAllJson() {
        try {
            return BusLineQuery.findAllBusLinesJson();
        } catch (Exception ex) {
            return Flux.empty();
        }
    }


    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            tags = {"BusLine"})
    public Mono<BusLineDto> findById(@PathVariable int id) {
        return BusLineQuery.findById(id);
    }

    @GetMapping(path = "findBusLineByName/{busLineName}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            tags = {"BusLine"})
    public Flux<BusLineDto> findBusLineByName(@PathVariable String busLineName) {
        try {
            return BusLineQuery.findBusLineByName(busLineName);
        } catch (Exception ex) {
            return Flux.empty();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"BusLine"})
    public Mono<BusLine> save(@Valid @RequestBody BusLine BusLine) {
        return BusLineCommand.save(BusLine);
    }

    @PostMapping("batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"BusLine"})
    public Flux<BusLine> saveBatch(@RequestBody List<BusLine> BusLines) {
        return BusLineCommand.saveAll(BusLines);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"BusLine"})
    public Mono<Void> update(@PathVariable int id, @Valid @RequestBody BusLine BusLine) {
        return BusLineCommand.update(BusLine.withId(id));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"BusLine"})
    public Mono<Void> delete(@PathVariable int id) {
        return BusLineCommand.delete(id);
    }

}
