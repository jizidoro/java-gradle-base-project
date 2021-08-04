/*
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

import reactor.core.publisher.Mono;

public abstract class UseCase<T> {

    protected abstract Mono<T> execute() throws Exception;
}
