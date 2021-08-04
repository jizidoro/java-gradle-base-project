/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;


import com.comrades.core.log.FuncionalidadeLogEnum;
import com.comrades.core.log.OperacaoLogEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LoggedUseCase {
    OperacaoLogEnum operacao();
    FuncionalidadeLogEnum funcionalidade();
    Class<?> clazz();
}
