package Entity.Actor;

import Entity.Branch.Branch;

public class Staff extends Person{

    private String username = "default";
    private String password = "password";
    private Branch branch;

    public Staff(int id, String name, int age, Gender gender, Branch branch) {
        super(id, name, age, gender);
        role = Role.STAFF;
        this.branch = branch;
    }

    public String getUsername() {
        return this.username;
    }

    public Role getStaffRole() {
        return this.role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validateAccount(String enteredUsername, String enteredPassword) {
        return this.password.equals(enteredPassword) && this.username.equals(enteredUsername);
    }
}
