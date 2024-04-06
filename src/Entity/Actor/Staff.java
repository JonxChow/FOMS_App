package Entity.Actor;

public class Staff extends Person{

    private String password = "password";

    public Staff(int id, String name) {
        super(id, name);
        role = Role.STAFF;
    }
}
