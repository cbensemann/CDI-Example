package nz.co.nomadconsulting.cdi.example.model;

/**
 * Model object used in our web service. This class could be annotated with JAXB annotations or Jackson (or other
 * xml/json provider specific annotations) if needed but we are just using Jacksons default serialization options in
 * this example.
 */
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (age != other.age) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        }
        else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
