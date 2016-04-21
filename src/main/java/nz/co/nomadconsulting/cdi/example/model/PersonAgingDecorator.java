package nz.co.nomadconsulting.cdi.example.model;

import java.util.Map;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 * Decorators are very similar to interceptors with the main difference being that interceptors function at a more technical level (method
 * interception) while decorators implement the interface of the thing they are decorating and are often more business focused.
 * <br/>
 * This is a somewhat contrived example where we are adjusting all the peoples ages by increasing them by {@link #AGE_OFFSET}. In practice you may
 * use a decorator like this to augment some data by combining it with data from a second datasource for example.
 * <br/>
 * This class must be enabled in beans.xml in order to be active
 */
@Decorator
public class PersonAgingDecorator implements DataService {

    private static final int AGE_OFFSET = 50;
    @Inject
    @Delegate
    private DataService delegate;


    @Override
    public Map<Long, Person> data() {
        final Map<Long, Person> data = delegate.data();
        data.forEach((k, p) -> p.setAge(p.getAge() + AGE_OFFSET));

        return data;
    }
}
