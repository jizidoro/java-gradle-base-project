package com.comrades.core.airplane.usecases;

import com.comrades.core.bases.UseCase;
import com.comrades.domain.models.Airplane;
import com.comrades.persistence.repositories.IAirplaneRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@Getter
@Setter
public class UcAirplaneCreate extends UseCase<Airplane> {

    @Autowired
    private IAirplaneRepository _airplaneRepository;

    private Airplane airplane;

    public UcAirplaneCreate(Airplane airplaneInput) {
        super();
        airplane = airplaneInput;
    }
    
    @Override
    protected Mono<Airplane> execute() throws Exception {
        return _airplaneRepository.save(airplane);
    }
}
