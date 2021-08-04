package com.comrades.application.services.busline.commands;

import com.comrades.application.mappers.BusLineMapper;
import com.comrades.application.services.busline.IBusLineCommand;
import com.comrades.application.services.busline.dtos.BusLineDto;
import com.comrades.core.bases.UseCaseFacade;
import com.comrades.core.busline.usecases.UcBusLineCreate;
import com.comrades.core.busline.usecases.UcBusLineDelete;
import com.comrades.core.busline.usecases.UcBusLineEdit;
import com.comrades.domain.models.BusLine;
import com.comrades.persistence.repositories.IBusLineRepository;
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
public class BusLineCommand implements IBusLineCommand {

    private final IBusLineRepository _busLineRepository;
    private final UseCaseFacade facade;

    public Mono<BusLine> save(BusLineDto busLine) {
        var result = BusLineMapper.INSTANCE.toBusLine(busLine);
        var uc = new UcBusLineCreate(result);
        return facade.execute(uc);
    }

    @Transactional
    public Flux<BusLine> saveAll(List<BusLine> BusLines) {
        return _busLineRepository.saveAll(BusLines)
                .doOnNext(this::throwResponseStatusExceptionWhenEmptyName);
    }

    private void throwResponseStatusExceptionWhenEmptyName(BusLine busLine) {
        if (StringUtil.isNullOrEmpty(busLine.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Name");
        }
    }

    public Mono<Void> update(BusLineDto busLine) {
        var result = BusLineMapper.INSTANCE.toBusLine(busLine);
        var uc = new UcBusLineEdit(result);
        return facade.execute(uc).then();
    }

    public Mono<Void> delete(int id) {
        var uc = new UcBusLineDelete(id);
        return facade.execute(uc).then();
    }
}
