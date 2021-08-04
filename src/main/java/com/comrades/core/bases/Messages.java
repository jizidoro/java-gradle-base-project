/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

import java.util.Properties;

/**
 * Permite a localizacao de mensagens especificas ou a obtenção de todas as mensagens existentes
 * para o locale atual do usuário.
 */
public interface Messages {

    /**
     * Retorna o mapa com todas as mensagens disponiveis no locale atual do usuario.
     *
     * @return mapa com as mensagens existentes no bundle do locale atual.
     */
    Properties getMessages();

    /**
     * Retorna a mensagem adequada ao locale atual ou a propria chave da mensagem contornada por '?'
     * caso nao exista no message bundle. Faz a interpolacao com os argumentos passados.
     *
     * @param code chave da mensage
     * @param args argumentos para interpolar na mensagem
     * @return mensagem ou o codigo da mensagem contornado por '?'.
     */
    String getMessage(String code, Object... args);

}
