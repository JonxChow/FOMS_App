package Entity.Actor;
public class Person {
    private int id;
    private String name;
    protected Role role;
    private int age;
    private Gender gender;

    public Person(){
        id = -1;
        name = "Undefined";
        this.role = Role.PERSON;
    }

    public Person(int id, String name, int age, Gender gender){
        this.id = id;
        this.name = name;
        this.role = Role.PERSON;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setId() {
        this.id = 1;
    }

    public void resetId() {
        this.id = -1;
    }

    public void setName(String name) {
        this.name = name;
    }

}
