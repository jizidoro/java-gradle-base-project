package com.comrades.core.bases;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class UseCaseLogger {

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());

    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


    public void log(ILoggedUseCase useCase) {
        if (!useCase.getClass().isAnnotationPresent(LoggedUseCase.class)) {
            throw new UnexpectedUseCaseException("O Caso de uso " + useCase.getClass().getSimpleName() + " implementa a interface ILoggedUseCase, mas não possui o atributo LoggedUseCase");
        }
        LoggedUseCase details = useCase.getClass().getAnnotation(LoggedUseCase.class);
    }


}