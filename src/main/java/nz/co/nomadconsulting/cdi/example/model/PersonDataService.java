package nz.co.nomadconsulting.cdi.example.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;

/**
 * This class provides data for our application and mimics a basic in-memory datastore. In practice you would probably back this service with a
 * database. The simplest way would be to use an {@link EntityManager}.
 * <br/>
 * Of note in this class is the scope annotation {@link ApplicationScoped} which effectively makes this class a singleton. Because it needs to exist
 * for the life of the application it is also created at start-up and we use the {@link PostConstruct} annotation to initialise some static data
 * which our application can use.
 * <br/>
 * Finally our data can be accessed either by injecting this service into your bean or by injecting a Map<Long, String> into your bean.
 */
@ApplicationScoped
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
