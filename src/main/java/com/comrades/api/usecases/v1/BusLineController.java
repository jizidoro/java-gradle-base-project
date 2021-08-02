package com.comrades.api.usecases.v1;


import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.application.services.busline.queries.BusLineQuery;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List all BusLines",
            tags = {"BusLine"})
    public Flux<BusLineDto> listAll() {
        try {
            return BusLineQuery.getAllBusLines();
        } catch (Exception ex) {
            return Flux.empty();
        }

    }

}
