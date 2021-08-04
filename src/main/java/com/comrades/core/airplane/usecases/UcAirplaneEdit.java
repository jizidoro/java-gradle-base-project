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
public class UcAirplaneEdit extends UseCase<Void> {

    @Autowired
    private IAirplaneRepository _airplaneRepository;

    private Airplane airplane;

    public UcAirplaneEdit(Airplane airplaneInput) {
        super();
        airplane = airplaneInput;
    }

    @Override
    protected Mono<Void> execute() throws Exception {
        return _airplaneRepository.findById(airplane.getId())
                .flatMap(_airplaneRepository::save)
                .then();
    }
}
