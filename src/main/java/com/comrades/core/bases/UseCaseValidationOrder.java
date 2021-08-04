/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Indica que a validacao do grupo lazy deve vir depois das validacoes no grupo padrao (sem grupo
 * definido) para os casos de uso. Se forem necessarios novos niveis de validaca, a anotacao
 * LazyValidation pode ser renomeada para SecondValidation e entao serem criadas outras anotacoes
 * para outros niveis (ThirdValidation, etc.) e estas serem declaradas na sequencia correta logo
 * abaixo.
 *
 * @author ur50
 */
@GroupSequence({Default.class, LazyValidation.class})
public interface UseCaseValidationOrder {

}
