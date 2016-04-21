package nz.co.nomadconsulting.cdi.example.model;

import java.util.Map;

import javax.enterprise.inject.Produces;

public interface DataService {

    Map<Long, Person> data();

}