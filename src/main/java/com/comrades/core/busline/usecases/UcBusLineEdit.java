package com.comrades.core.busline.usecases;

import com.comrades.core.bases.UseCase;
import com.comrades.domain.models.BusLine;
import com.comrades.persistence.repositories.IBusLineRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@Getter
@Setter
public class UcBusLineEdit extends UseCase<Void> {

    @Autowired
    private IBusLineRepository _busLineRepository;

    private BusLine busLine;

    public UcBusLineEdit(BusLine busLineInput) {
        super();
        busLine = busLineInput;
    }

    @Override
    protected Mono<Void> execute() throws Exception {
        return _busLineRepository.findById(busLine.getId())
                .flatMap(_busLineRepository::save)
                .then();
    }
}
