package com.comrades.core.airplane.usecases;

import com.comrades.core.bases.UseCase;
import com.comrades.persistence.repositories.IAirplaneRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@Getter
@Setter
public class UcAirplaneDelete extends UseCase<Void> {

    @Autowired
    private IAirplaneRepository _airplaneRepository;

    private int id;

    public UcAirplaneDelete(int airplaneId) {
        super();
        id = airplaneId;
    }

    @Override
    protected Mono<Void> execute() throws Exception {
        var result = _airplaneRepository.findById(id)
                .flatMap(_airplaneRepository::delete);

        return result;
    }
}
