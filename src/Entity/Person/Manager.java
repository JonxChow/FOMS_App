package Entity.Person;

public class Manager extends Staff{
    public Manager(int id, String name) {
        super(id, name);
        role = Role.MANAGER;
    }

    // Manager-specific functionalities
    void displayStaffList(){}

    void editMenu(){}
}
