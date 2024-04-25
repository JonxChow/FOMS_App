package Boundary;

import Entity.Actor.Manager;
import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IStaffUI;
import Interface.Controllers.ILoginController;
import Interface.Display.IDisplayMenu;

/**
 * Handles user login, account creation, and role-based access to different functionalities.
 * This class acts as an interface between the user and the system for accessing various functionalities
 * depending on the user's role and authentication status.
 */
public class LoginUI implements IDisplayMenu {

    private IAllBranches allBranches;
    private ILoginController loginController;
    private IStaffUI staffUI;
    private ManagerUI managerUI;
    private AdminUI adminUI;

    /**
     * Constructs the LoginUI with dependencies needed for operation.
     * @param allBranches Interface for accessing branch data.
     * @param loginController Controller for handling login and account management.
     * @param staffUI User interface for staff actions.
     * @param managerUI User interface for manager actions.
     * @param adminUI User interface for administrative actions.
     */
    public LoginUI(IAllBranches allBranches, ILoginController loginController, IStaffUI staffUI, ManagerUI managerUI, AdminUI adminUI) {
        this.allBranches = allBranches;
        this.loginController = loginController;
        this.staffUI = staffUI;
        this.managerUI = managerUI;
        this.adminUI = adminUI;
    }

    /**
     * Displays the main login menu and handles user input to navigate to different functionalities
     * based on the user's choice and authentication status.
     */
    @Override
    public void displayMenu() {
        int choice;
        Role role;
        Branch branch;
        String enteredUsername, enteredPassword, newUsername, newPassword;

        do {
            System.out.println("1: Login");
            System.out.println("2: Create an Account");
            System.out.println("3: Admin Access");
            System.out.println("4: Change Username/Password");
            System.out.println("5: Exit");

            choice = InputHelper.getValidatedInt("Select Option: ", 1, 5);

            switch(choice){
                case 1:
                    branch = getBranch();
                    if (branch == null) {
                        System.out.println("Branch not found");
                        continue;
                    }
                    enteredUsername = InputHelper.getValidatedString("Enter username: ");
                    enteredPassword = InputHelper.getValidatedString("Enter password ");
                    if (loginController.validateAccount(branch, enteredUsername, enteredPassword)) {
                        role = loginController.getRoleByUsername(branch, enteredUsername);
                        switch (role) {
                            case STAFF:
                                staffUI.setBranch(branch);
                                staffUI.displayMenu();
                                break;
                            case MANAGER:
                                managerUI.setBranch(branch);
                                managerUI.displayMenu();
                                break;
                        }
                    } else {
                        System.out.println("Incorrect Username or Password");
                    }
                    break;
                case 2:
                    branch = getBranch();
                    if (branch == null) {
                        System.out.println("Branch doesn't exist");
                        continue;
                    }
                    role = selectRole();
                    Staff staff = loginController.getAvailableAccount(branch, role);
                    if (staff == null) {
                        System.out.println("No available accounts");
                        break;
                    }
                    newUsername = InputHelper.getValidatedString("Enter new Username: ");
                    newPassword = InputHelper.getValidatedString("Enter new Password: ");
                    if (loginController.setAccountDetails(branch, staff.getUsername(), newUsername, newPassword)) {
                        loginController.claimAccount(branch, staff);
                        System.out.println("Account Creation Successful");
                    } else {
                        System.out.println("Account Creation Unsuccessful");
                    }
                    break;
                case 3:
                    authenticateAdmin();
                    break;
                case 4:
                    changeUserCredentials();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
            }
        } while (choice < 5);
    }

    /**
     * Prompts the user to select a role during the account creation process.
     * @return the selected role, either STAFF or MANAGER.
     */
    private Role selectRole() {
        while (true) {
            String roleName = InputHelper.getValidatedString("Enter your role: ");
            if ("STAFF".equalsIgnoreCase(roleName)) {
                return Role.STAFF;
            } else if ("MANAGER".equalsIgnoreCase(roleName)) {
                return Role.MANAGER;
            } else {
                System.out.println("Invalid Role");
            }
        }
    }

    /**
     * Authenticates administrative access.
     */
    private void authenticateAdmin() {
        while (true) {
            String enteredUsername = InputHelper.getValidatedString("Enter username: ");
            String enteredPassword = InputHelper.getValidatedString("Enter password ");
            if ("Admin".equals(enteredUsername) && "Admin".equals(enteredPassword)) {
                adminUI.displayMenu();
                break;
            } else {
                System.out.println("Incorrect Username or Password");
            }
        }
    }

    /**
     * Changes the username and/or password for a user after successful authentication.
     */
    private void changeUserCredentials() {
        Branch branch = getBranch();
        if (branch == null) {
            System.out.println("Branch doesn't exist");
            return;
        }
        String enteredUsername = InputHelper.getValidatedString("Enter username: ");
        String enteredPassword = InputHelper.getValidatedString("Enter password ");
        if (loginController.validateAccount(branch, enteredUsername, enteredPassword)) {
            String newUsername = InputHelper.getValidatedString("Enter new Username: ");
            String newPassword = InputHelper.getValidatedString("Enter new Password: ");
            if (loginController.setAccountDetails(branch, enteredUsername, newUsername, newPassword)) {
                System.out.println("Credentials updated successfully.");
            } else {
                System.out.println("Failed to update credentials.");
            }
        } else {
            System.out.println("Login failed. Incorrect credentials.");
        }
    }

    /**
     * Retrieves a branch by name to perform role-specific actions.
     * @return the found branch or null if the branch does not exist.
     */
    private Branch getBranch() {
        String branchName = InputHelper.getValidatedString("Enter Branch Name to Access: ");
        return allBranches.getBranchByName(branchName);
    }
}
