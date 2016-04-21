package nz.co.nomadconsulting.cdi.example.model;

import java.util.Map;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class PersonAgingDecorator implements DataService {

    @Inject
    @Delegate
    private DataService delegate;


    @Override
    public Map<Long, Person> data() {
        final Map<Long, Person> data = delegate.data();
        data.forEach((k, p) -> p.setAge(p.getAge() + 50));

        return data;
    }
}
