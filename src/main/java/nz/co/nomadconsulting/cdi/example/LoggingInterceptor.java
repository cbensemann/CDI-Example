package nz.co.nomadconsulting.cdi.example;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * An example interceptor. Notice it is annotated with the qualifier @Logged and we use an empty String as the value. This is because the value is
 * marked {@link Nonbinding} so essentially this interceptor will match all cases where @Logged is used. Without {@link Nonbinding} it would have to
 * match the value exactly to be used.
 * <br/>
 * This class must be enabled in beans.xml in order to be active
 */
@Interceptor
@Logged("")
public class LoggingInterceptor {

    /**
     * This is where the magic happens. The method needs to take an {@link InvocationContext} and return type of Object. @AroundInvoke is used to
     * indicate that this method will intercept a method annotated with @Logged.
     * <br/>
     * In this method you can perform any kind of action before or after the method invokation.
     *
     * @param ctx the context that we are intercepting
     * @return this is usually (but not always) the result of the ctx.proceed method.
     *
     * @throws Exception
     */
    @AroundInvoke
    public Object log(final InvocationContext ctx) throws Exception {
        final Method method = ctx.getMethod();
        final String fullMethodName = method.getDeclaringClass().getName() + "#" + method.getName();
        final Logged methodAnnotation = method.getAnnotation(Logged.class);
        final Logged classAnnotation = method.getDeclaringClass().getAnnotation(Logged.class);

        Logger log = null;
        Level level = null;
        if (logAnnotationIsPresent(methodAnnotation, classAnnotation)) {
            level = getLevel(methodAnnotation, classAnnotation);
            log = Logger.getLogger(method.getDeclaringClass().getName());
            log.log(level, "about to execute " + fullMethodName);
        }

        final Object result = ctx.proceed();

        if (log != null) {
            log.log(level, "finished executing " + fullMethodName);
        }

        return result;
    }


    private boolean logAnnotationIsPresent(final Logged methodAnnotation, final Logged classAnnotation) {
        return methodAnnotation != null || classAnnotation != null;
    }


    private Level getLevel(final Logged methodAnnotation, final Logged classAnnotation) {
        if (methodAnnotation != null) {
            return Level.parse(methodAnnotation.value());
        }
        else {
            return Level.parse(classAnnotation.value());
        }
    }
}
