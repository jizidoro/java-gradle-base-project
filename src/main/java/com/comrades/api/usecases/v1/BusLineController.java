package com.comrades.api.usecases.v1;


import com.comrades.application.services.busline.IBusLineCommand;
import com.comrades.application.services.busline.IBusLineQuery;
import com.comrades.application.services.busline.dtos.BusLineDto;
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

    private final IBusLineQuery _busLineQuery;
    private final IBusLineCommand _busLineCommand;

    @GetMapping(path = "listAll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all BusLines DB",
            tags = {"BusLine"})
    public Flux<BusLineDto> listAll() {
        try {
            return _busLineQuery.findAll();
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
            return _busLineQuery.findAllBusLinesJson();
        } catch (Exception ex) {
            return Flux.empty();
        }
    }


    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            tags = {"BusLine"})
    public Mono<BusLineDto> findById(@PathVariable int id) {
        try {
            return _busLineQuery.findById(id);
        } catch (Exception ex) {
            return Mono.empty();
        }
    }

    @GetMapping(path = "findBusLineByName/{busLineName}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            tags = {"BusLine"})
    public Flux<BusLineDto> findBusLineByName(@PathVariable String busLineName) {
        try {
            return _busLineQuery.findBusLineByName(busLineName);
        } catch (Exception ex) {
            return Flux.empty();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"BusLine"})
    public Mono<BusLine> save(@Valid @RequestBody BusLineDto busLine) {
        try {
            return _busLineCommand.save(busLine);
        } catch (Exception ex) {
            return Mono.empty();
        }
    }

    @PostMapping("batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            tags = {"BusLine"})
    public Flux<BusLine> saveBatch(@RequestBody List<BusLine> BusLines) {
        try {
            return _busLineCommand.saveAll(BusLines);
        } catch (Exception ex) {
            return Flux.empty();
        }
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"BusLine"})
    public Mono<Void> update(@PathVariable int id, @Valid @RequestBody BusLineDto busLine) {
        try {
            return _busLineCommand.update(busLine);
        } catch (Exception ex) {
            return Mono.empty();
        }
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            tags = {"BusLine"})
    public Mono<Void> delete(@PathVariable int id) {
        try {
            return _busLineCommand.delete(id);
        } catch (Exception ex) {
            return Mono.empty();
        }
    }

}
