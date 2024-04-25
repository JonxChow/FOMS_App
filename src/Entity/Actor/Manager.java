package Entity.Actor;

public class Manager extends Staff{
    private static final long serialVersionUID = 1L;
    public Manager(int id, String name, int age, Gender gender) {
        super(id, name, age, gender);
        role = Role.MANAGER;
    }

    // Manager-specific functionalities
    void displayStaffList(){}

    void editMenu(){}
}
