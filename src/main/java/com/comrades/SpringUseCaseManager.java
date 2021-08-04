/* 
 * Este arquivo pertence a Petrobras e nao pode ser utilizado fora desta empresa 
 * sem previa autorizacao.
 * ----------------------------------
 * Esta classe segue o padrao PE-2T0-00250
 */
package com.comrades;


import com.comrades.core.bases.*;
import com.comrades.core.log.UseCaseLogContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Implementacao de {@link UseCaseManager} baseado no spring. Considera que todos os use cases serao
 * necessariamente prototypes.
 */
@Component
public class SpringUseCaseManager implements UseCaseManager, BeanFactoryAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringUseCaseManager.class);

    private AutowireCapableBeanFactory beanFactory;

    private final UseCaseLogger useCaseLogger;

    @Autowired
    public SpringUseCaseManager(UseCaseLogContext contextoOperacao, UseCaseLogger useCaseLogger) {
        this.useCaseLogger = useCaseLogger;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (AutowireCapableBeanFactory) beanFactory;
    }

    private boolean shouldLogOnPersistence(UseCase usecase) {
        //Objetos com essa interface são logados pelo Logger mais novo.
        if (ReflectionHelper.implementsInterface(usecase, ILoggedUseCase.class)) {
            return false;
        }
        return usecase.getClass().isAnnotationPresent(LoggedUseCase.class);
    }

    @Override
    public void prepare(UseCase usecase) {
        beanFactory.autowireBean(usecase);
    }

    @Override
    public void complete(UseCase usecase) {
        try {
            if (!shouldLogOnPersistence(usecase)) {
                if (ReflectionHelper.implementsInterface(usecase, ILoggedUseCase.class)) {
                    useCaseLogger.log((ILoggedUseCase) usecase);
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Falha ao completar usecase: " + usecase.getClass().getName(), e);
        }
    }

    @Override
    public void destroy(UseCase usecase) {
        try {
            beanFactory.destroyBean(usecase);
        } catch (Exception e) {
            LOGGER.warn("Falha ao destruir usecase: " + usecase.getClass().getName(), e);
        }
    }
}
