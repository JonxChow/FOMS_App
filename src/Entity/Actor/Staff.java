package Entity.Actor;

import Entity.Branch.Branch;

import java.io.Serializable;

/**
 * Represents a staff member within the system, extending the {@code Person} class with staff-specific properties
 * like username and password. This class is used for managing staff account information and authentication.
 */
public class Staff extends Person {
    private static final long serialVersionUID = 1L;
    private String username = "Staff";
    private String password = "password";

    /**
     * Constructs a {@code Staff} with specified ID, name, age, and gender.
     * Initializes the staff member with default role {@code Role.STAFF} and default credentials.
     *
     * @param id The identification number of the staff member.
     * @param name The name of the staff member.
     * @param age The age of the staff member.
     * @param gender The gender of the staff member, as defined by the {@code Gender} enum.
     */
    public Staff(int id, String name, int age, Gender gender) {
        super(id, name, age, gender);
        role = Role.STAFF;
    }

    /**
     * Returns the username of the staff member.
     *
     * @return the username of the staff member.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the role of the staff member as defined by the {@code Role} enumeration.
     *
     * @return the staff member's role.
     */
    public Role getStaffRole() {
        return this.role;
    }

    /**
     * Sets the username for the staff member.
     *
     * @param username the new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password for the staff member.
     *
     * @param password the new password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Validates the entered username and password against the staff member's stored credentials.
     *
     * @param enteredUsername The username entered for login.
     * @param enteredPassword The password entered for login.
     * @return {@code true} if both the username and password match the stored credentials, {@code false} otherwise.
     */
    public boolean validateAccount(String enteredUsername, String enteredPassword) {
        return this.password.equals(enteredPassword) && this.username.equals(enteredUsername);
    }
}
