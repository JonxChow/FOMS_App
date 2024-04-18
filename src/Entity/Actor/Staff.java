package Entity.Actor;

public class Staff extends Person{

    private String password = "password";

    public Staff(int id, String name, int age, Gender gender) {
        super(id, name, age, gender);
        role = Role.STAFF;
    }
}
