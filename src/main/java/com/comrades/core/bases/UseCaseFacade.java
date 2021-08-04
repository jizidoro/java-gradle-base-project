/*
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Component
public class UseCaseFacade {

    private final UseCaseManager manager;

    private final Validator validator;

    @Autowired
    public UseCaseFacade(UseCaseManager manager, Validator validator) {
        this.manager = manager;
        this.validator = validator;
    }

    @Transactional
    public <T> Mono<T> execute(UseCase<T> usecase) {
        manager.prepare(usecase);
        validate(usecase);
        try {
            Mono<T> res = executeAndHandleExceptions(usecase);
            manager.complete(usecase);
            return res;
        } finally {
            manager.destroy(usecase);
        }
    }

    private <T> Mono<T> executeAndHandleExceptions(UseCase<T> prepared) {
        try {
            return prepared.execute();
        } catch (ConstraintViolationException ex) {
            throw new BusinessException(
                    new HashSet<ConstraintViolation>(ex.getConstraintViolations()));
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception e) {
            throw new UnexpectedUseCaseException(prepared.getClass(), e);
        }
    }

    protected void validate(Object usecase) {
        Set<ConstraintViolation<Object>> violations = validator.validate(usecase,
                UseCaseValidationOrder.class);
        if (!violations.isEmpty()) {
            throw new BusinessException(new HashSet<ConstraintViolation>(violations));
        }
    }

}
