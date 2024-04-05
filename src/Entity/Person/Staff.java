package Entity.Person;

public class Staff extends Person{
    public Staff(int id, String name) {
        super(id, name);
        role = Role.STAFF;
    }
}
