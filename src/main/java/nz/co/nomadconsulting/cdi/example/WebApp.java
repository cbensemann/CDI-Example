package nz.co.nomadconsulting.cdi.example;

import nz.co.nomadconsulting.cdi.example.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/person")
@Logged
public class WebApp {

    @Inject
    private Map<Long, Person> data;

    @Path("/{id}")
    @Produces("application/json")
    @GET
    @Logged("WARN")
    public Person getPerson(@PathParam("id") final Long id) {
        return data.get(id);
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
