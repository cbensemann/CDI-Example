package nz.co.nomadconsulting.cdi.example.model;

public class Person {

    private Long id;

    private String name;

    private int age;


    public Person() {
    }

    public Person(final long id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public Long getId() {
        return id;
    }


    public void setId(final Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }


    public void setAge(final int age) {
        this.age = age;
    }
}
