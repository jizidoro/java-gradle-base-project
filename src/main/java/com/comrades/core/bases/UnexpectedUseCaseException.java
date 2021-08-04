/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

/**
 * Lançada caso ocorra um erro inesperado na execução de um caso de uso.
 */
public class UnexpectedUseCaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected UnexpectedUseCaseException() {
        super();
    }

    protected UnexpectedUseCaseException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected UnexpectedUseCaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedUseCaseException(String message) {
        super(message);
    }

    protected UnexpectedUseCaseException(Throwable cause) {
        super(cause);
    }

    protected UnexpectedUseCaseException(Class<?> useCaseType, Throwable cause) {
        super("Erro ao executar caso de uso " + useCaseType.getSimpleName()
                + ": " + cause.getLocalizedMessage(), cause);
    }

}
