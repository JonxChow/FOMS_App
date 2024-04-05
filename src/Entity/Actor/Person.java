package Entity.Actor;
public class Person {
    private int id;
    private String name;
    protected Role role;
    public Person(){
        id = -1;
        name = "Undefined";
        this.role = Role.PERSON;
    }

    public Person(int id, String name){
        this.id = id;
        this.name = name;
        role = Role.PERSON;
    }
}
