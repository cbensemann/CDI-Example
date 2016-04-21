package nz.co.nomadconsulting.cdi.example.model;

import java.util.Map;

import javax.decorator.Decorator;


/**
 * Simple interface created so we can use it in our {@link Decorator} example.
 */
public interface DataService {

    Map<Long, Person> data();

}