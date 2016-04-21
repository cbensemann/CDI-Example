package nz.co.nomadconsulting.cdi.example;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * Used to mark classes or methods that are to be intercepted and logged
 */
@Inherited
@Documented
@InterceptorBinding
@Target({ METHOD, TYPE })
@Retention(RUNTIME)
public @interface Logged {

    /**
     * Optional logging Level. Defaults to INFO if not provided.
     */
    @Nonbinding
    String value() default "INFO";
}
