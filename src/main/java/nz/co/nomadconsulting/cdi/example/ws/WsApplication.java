package nz.co.nomadconsulting.cdi.example.ws;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Not really directly related to the CDI demo this class just starts the JAX RS stuff up and makes it accessible under the path '/api'
 */
@ApplicationPath("/api")
public class WsApplication extends Application {

}
