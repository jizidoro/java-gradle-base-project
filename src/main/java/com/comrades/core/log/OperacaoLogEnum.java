/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.log;

public enum OperacaoLogEnum implements PersistentEnum {
    ALTERACAO(1L),
    EXCLUSAO(2L),
    INCLUSAO(3L),
    LOGIN(4L),
    LOGOFF(5L);

    private final Long codigo;

    OperacaoLogEnum(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public final Long getCodigo() {
        return codigo;
    }
}
