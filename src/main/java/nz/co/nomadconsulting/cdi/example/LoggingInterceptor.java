package nz.co.nomadconsulting.cdi.example;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logged("")
public class LoggingInterceptor {

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
