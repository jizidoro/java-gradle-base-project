/*
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Exceção que deve ser usada no pacote business para erros de negocio.
 */
@SuppressWarnings("rawtypes")
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	private final transient Set<ConstraintViolation> violations;

    public BusinessException() {
        super();
        violations = null;
    }

    public BusinessException(String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        violations = null;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        violations = null;
    }

    public BusinessException(String message) {
        super(message);
        violations = null;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        violations = null;
    }

    public BusinessException(Set<ConstraintViolation> violations) {
        this.violations = CloneUtils.clone(violations);
    }

    public Set<ConstraintViolation> getViolations() {
        return CloneUtils.clone(violations);
    }
}
