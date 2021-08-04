package com.comrades.application.services.airplane.commands;

import com.comrades.application.mappers.AirplaneMapper;
import com.comrades.application.services.airplane.IAirplaneCommand;
import com.comrades.application.services.airplane.dtos.AirplaneDto;
import com.comrades.core.airplane.usecases.UcAirplaneCreate;
import com.comrades.core.airplane.usecases.UcAirplaneDelete;
import com.comrades.core.airplane.usecases.UcAirplaneEdit;
import com.comrades.core.bases.UseCaseFacade;
import com.comrades.domain.models.Airplane;
import com.comrades.persistence.repositories.IAirplaneRepository;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirplaneCommand implements IAirplaneCommand {

    private final IAirplaneRepository _airplaneRepository;
    private final UseCaseFacade facade;

    public Mono<Airplane> save(AirplaneDto airplane) {
        var result = AirplaneMapper.INSTANCE.toAirplane(airplane);
        var uc = new UcAirplaneCreate(result);
        return facade.execute(uc);
    }

    @Transactional
    public Flux<Airplane> saveAll(List<Airplane> airplanes) {
        return _airplaneRepository.saveAll(airplanes)
                .doOnNext(this::throwResponseStatusExceptionWhenEmptyName);
    }

    private void throwResponseStatusExceptionWhenEmptyName(Airplane airplane) {
        if (StringUtil.isNullOrEmpty(airplane.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Name");
        }
    }

    public Mono<Void> update(AirplaneDto airplane) {
        var result = AirplaneMapper.INSTANCE.toAirplane(airplane);
        var uc = new UcAirplaneEdit(result);
        return facade.execute(uc).then();
    }

    public Mono<Void> delete(int id) {
        var uc = new UcAirplaneDelete(id);
        return facade.execute(uc).then();
    }
}
