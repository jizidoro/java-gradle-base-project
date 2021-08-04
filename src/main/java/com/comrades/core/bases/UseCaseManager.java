/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

/**
 * Prepara o caso de uso para execucao, fazendo a injecao das dependencias ou desalocando as
 * dependencias injetadas ao invocar o metodo destroy.
 */
public interface UseCaseManager {

    void prepare(UseCase usecase);

    void complete(UseCase usecase);

    void destroy(UseCase usecase);
}
