/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades.core.bases;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Properties;

/**
 * Adaptador de {@link ReloadableResourceBundleMessageSource} a interface {@link Messages}.
 */
public class MessageBundle extends ReloadableResourceBundleMessageSource implements Messages {

    private final String basename;

    public MessageBundle(String basename) {
        this.basename = basename;
    }
    
    /**
     * @see Messages
     */
    @Override
    public Properties getMessages() {
        Properties props = this.getProperties(basename + "_"
                + LocaleContextHolder.getLocale()).getProperties();
        if (props == null || props.isEmpty()) {
            props = this.getProperties(basename).getProperties();
        }
        if (props == null || props.isEmpty()) {
            props = this.getMergedProperties(LocaleContextHolder.getLocale())
                    .getProperties();
        }
        return props;
    }

    /**
     * @see Messages
     */
    @Override
    public String getMessage(String code, Object... args) {
        return this.getMessage(code, args, "?" + code + "?", LocaleContextHolder.getLocale());
    }

}
