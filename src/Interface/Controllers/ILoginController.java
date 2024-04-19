package Interface.Controllers;

import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;

public interface ILoginController {

    boolean validateAccount(Branch branch, String enteredUsername, String enteredPassword);

    Role getRoleByUsername(Branch branch, String username);

    Staff getAvailableAccount(Branch branch, Role role);

    boolean setAccountDetails(Branch branch, String oldUsername, String newUsername, String newPassword);

    void claimAccount(Branch branch, Staff staff);
}
