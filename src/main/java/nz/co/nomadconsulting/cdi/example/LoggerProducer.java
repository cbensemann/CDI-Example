package nz.co.nomadconsulting.cdi.example;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Example Producer. Creates a Logger to be injected at the given Injection point. The injection point is used to get the class name of the bean that
 * this Logger will be injected into so that the right logger can be created.
 */
public class LoggerProducer {

    @Produces
    public Logger produceLogger(final InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}