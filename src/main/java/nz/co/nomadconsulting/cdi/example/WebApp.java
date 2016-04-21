package nz.co.nomadconsulting.cdi.example;

import nz.co.nomadconsulting.cdi.example.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * The main "business" class of the application. This class exposes several methods as a restful web service.
 * <br/>
 * Notice we are using the @Logged annotation at the class level so all public methods will be intercepted by our {@link LoggingInterceptor}. We are
 * also injecting a logger which is created from our {@link LoggerProducer}.
 */
@Path("/person")
@Logged
public class WebApp {

    @Inject
    private Logger log;

    @Inject
    private Map<Long, Person> data;

    @Path("/{id}")
    @Produces("application/json")
    @GET
    @Logged("WARN")
    public Person getPerson(@PathParam("id") final Long id) {
        final Person person = data.get(id);
        if (person == null) {
            log.warning("No person found for id: " + id);
        }
        return person;
    }


    @Path("/")
    @Produces("application/json")
    @GET
    @Logged("DEBUG")
    public List<Person> getPeople() {
        return new ArrayList<>(data.values());
    }


    @Path("/add")
    @POST
    @Consumes("application/json")
    public Response addPerson(final Person person) {
        data.put(person.getId(), person);

        return Response.ok().build();
    }
}
