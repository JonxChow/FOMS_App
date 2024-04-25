package Entity.Actor;

/**
 * The {@code Manager} class represents a specific type of staff member with managerial privileges.
 * It extends the {@code Staff} class, inheriting its basic properties such as ID, name, age, and gender,
 * and sets the role specific to a manager.
 * <p>
 * The {@code serialVersionUID} attribute is used to ensure that a serialized Manager object can
 * be deserialized even if there are changes to the class, as long as the changes are compatible.
 * </p>
 */
public class Manager extends Staff{
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code Manager} with the specified ID, name, age, and gender.
     * The role is set to {@code Role.MANAGER}, and a default username is established.
     *
     * @param id The identification number of the manager.
     * @param name The name of the manager.
     * @param age The age of the manager.
     * @param gender The gender of the manager, specified by the {@code Gender} enum.
     */
    public Manager(int id, String name, int age, Gender gender) {
        super(id, name, age, gender);
        role = Role.MANAGER;
        super.setUsername("Manager");
    }
}
