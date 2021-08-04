/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

/**
 * Interface para enumeracoes que devem ser mapeadas pelo framework de persistencia. Alem de herdar
 * essa interface, para cada nova enumeracao adicionada, deve-se criar um custom type no pacote
 * external.persistence.types herdando de EnumType e incluir uma declaracao @TypeDef no arquivo
 * package-info.java desse mesmo pacote, de forma que o framework reconheca o novo tipo e faça o
 * mapeamento automatico.
 */
@FunctionalInterface
public interface PersistentEnum {

    /**
     * Deve retornar o valor real que deve ser persistido no banco de dados.
     *
     * @return valor real a ser salvo no banco de dados
     */
    Object getCodigo();

}
