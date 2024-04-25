package Entity.Actor;

import java.io.Serializable;

/**
 * Represents a person with basic personal details and functionalities.
 * This class serves as a foundation for more specific types of people within the system,
 * such as staff or managers, by providing common attributes like ID, name, age, and gender.
 * <p>
 * This class is serializable to allow instances of this class to be serialized and deserialized,
 * which is useful for storing or transmitting objects over a network.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    protected Role role;
    private int age;
    private Gender gender;

    /**
     * Constructs a default {@code Person} with ID set to -1, name to "Undefined", and role to {@code Role.PERSON}.
     */
    public Person(){
        id = -1;
        name = "Undefined";
        this.role = Role.PERSON;
    }

    /**
     * Constructs a {@code Person} with specified ID, name, age, and gender.
     *
     * @param id The identification number of the person.
     * @param name The name of the person.
     * @param age The age of the person.
     * @param gender The gender of the person, specified by the {@code Gender} enum.
     */
    public Person(int id, String name, int age, Gender gender){
        this.id = id;
        this.name = name;
        this.role = Role.PERSON;
        this.age = age;
        this.gender = gender;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the identification number of the person.
     *
     * @return the identification number.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the identification number of the person to 1.
     */
    public void setId() {
        this.id = 1;
    }

    /**
     * Resets the identification number of the person to -1.
     */
    public void resetId() {
        this.id = -1;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the role of the person.
     *
     * @return the role of the person.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the person.
     *
     * @param role the new role to be set.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns the gender of the person.
     *
     * @return the gender of the person.
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Returns the age of the person.
     *
     * @return the age of the person.
     */
    public int getAge() {
        return this.age;
    }
}
