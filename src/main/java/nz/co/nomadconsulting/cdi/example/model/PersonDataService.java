package nz.co.nomadconsulting.cdi.example.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;

@ApplicationScoped
@Startup
public class PersonDataService implements DataService {

    private Map<Long, Person> data = new HashMap<>();

    @PostConstruct
    public void init() {
        final Person bob = new Person(1, "Bob", 23);
        final Person mary = new Person(2, "Mary", 33);
        final Person peter = new Person(3, "Peter", 47);
        final Person rick = new Person(4, "Rick", 18);
        final Person jane = new Person(5, "Jane", 29);
        data.put(bob.getId(), bob);
        data.put(mary.getId(), mary);
        data.put(peter.getId(), peter);
        data.put(rick.getId(), rick);
        data.put(jane.getId(), jane);
    }


    @Override
    @Produces
    public Map<Long, Person> data() {
        return data;
    }
}
