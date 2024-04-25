package Controller;

import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Controllers.ILoginController;

/**
 * Manages login and account validation processes for staff within branches.
 * This controller facilitates account validation, role retrieval, and account updating functionalities.
 */
public class LoginController implements ILoginController {

    /**
     * Validates the account credentials of a staff member within a specified branch.
     *
     * @param branch The branch where the account resides.
     * @param enteredUsername The username entered for login.
     * @param enteredPassword The password entered for login.
     * @return true if the credentials are correct and the account exists, false otherwise.
     */
    @Override
    public boolean validateAccount(Branch branch, String enteredUsername, String enteredPassword) {
        Staff staff = branch.getStaffByUsername(enteredUsername);
        if (staff == null) {
            System.out.println("Account doesn't exist");
            return false;
        } else {
            return staff.validateAccount(enteredUsername, enteredPassword);
        }
    }

    /**
     * Retrieves the role of a staff member based on the username within a specific branch.
     *
     * @param branch The branch where the staff member is located.
     * @param username The username of the staff member.
     * @return The role of the staff member.
     */
    public Role getRoleByUsername(Branch branch, String username) {
        Staff staff = branch.getStaffByUsername(username);
        return staff != null ? staff.getStaffRole() : null;
    }

    /**
     * Retrieves an unassigned account within a branch that matches the specified role.
     *
     * @param branch The branch to search within.
     * @param role The role of the account to find.
     * @return An unassigned staff member with the specified role, or null if none found.
     */
    @Override
    public Staff getAvailableAccount(Branch branch, Role role) {
        return branch.getUnassignedStaff(role);
    }

    /**
     * Updates the username and password of an existing account within a branch.
     *
     * @param branch The branch where the account is located.
     * @param enteredUsername The current username of the account.
     * @param newUsername The new username to set.
     * @param newPassword The new password to set.
     * @return true if the account details were successfully updated, false if the account does not exist.
     */
    @Override
    public boolean setAccountDetails(Branch branch, String enteredUsername, String newUsername, String newPassword) {
        Staff staff = branch.getStaffByUsername(enteredUsername);
        if (staff == null) return false;

        staff.setUsername(newUsername);
        staff.setPassword(newPassword);

        return true;
    }

    /**
     * Claims an account for use by marking it as assigned to a staff member.
     *
     * @param branch The branch where the account is located.
     * @param staff The staff member claiming the account.
     */
    @Override
    public void claimAccount(Branch branch, Staff staff) {
        Staff existingStaff = branch.getStaffByName(staff.getName());
        if (existingStaff != null) {
            existingStaff.setId(); // Assuming setId is meant to mark the staff as assigned.
        }
    }
}
