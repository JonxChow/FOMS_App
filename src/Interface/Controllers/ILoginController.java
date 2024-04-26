package Interface.Controllers;

import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;

/**
 * Interface that defines the methods for controlling login-related operations.
 */
public interface ILoginController {

    /**
     * Validates the provided username and password for a given branch.
     *
     * @param branch         the branch for which the account is being validated
     * @param enteredUsername the entered username
     * @param enteredPassword the entered password
     * @return true if the username and password are valid, false otherwise
     */
    boolean validateAccount(Branch branch, String enteredUsername, String enteredPassword);

    /**
     * Retrieves the role associated with the given username for a specific branch.
     *
     * @param branch   the branch for which the role is being retrieved
     * @param username the username
     * @return the role associated with the username
     */
    Role getRoleByUsername(Branch branch, String username);

    /**
     * Retrieves an available account for the given role and branch.
     *
     * @param branch the branch for which the account is being retrieved
     * @param role   the role of the account
     * @return a staff object representing an available account
     */
    Staff getAvailableAccount(Branch branch, Role role);

    /**
     * Sets new account details (username and password) for an existing account.
     *
     * @param branch      the branch for which the account details are being updated
     * @param oldUsername the current username of the account
     * @param newUsername the new username for the account
     * @param newPassword the new password for the account
     * @return true if the account details were successfully updated, false otherwise
     */
    boolean setAccountDetails(Branch branch, String oldUsername, String newUsername, String newPassword);

    /**
     * Claims an available account for a specific staff member in a given branch.
     *
     * @param branch the branch for which the account is being claimed
     * @param staff  the staff member claiming the account
     */
    void claimAccount(Branch branch, Staff staff);
}