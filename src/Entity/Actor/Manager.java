package Entity.Actor;

public class Manager extends Staff{
    public Manager(int id, String name, int age, Gender gender) {
        super(id, name, age, gender);
        role = Role.MANAGER;
    }

    // Manager-specific functionalities
    void displayStaffList(){}

    void editMenu(){}
}
