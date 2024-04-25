package Boundary;

import Entity.Actor.Manager;
import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Controllers.ILoginController;
import Interface.Display.IDisplayMenu;

public class LoginUI implements IDisplayMenu {

    private  IAllBranches allBranches;
    private ILoginController loginController;

    private StaffUI staffUI;

    private ManagerUI managerUI;

    private AdminUI adminUI;

    public LoginUI(IAllBranches allBranches, ILoginController loginController, StaffUI staffUI, ManagerUI managerUI, AdminUI adminUI) {
        this.allBranches = allBranches;
        this.loginController = loginController;
        this.staffUI = staffUI;
        this.managerUI = managerUI;
        this.adminUI = adminUI;
    }


    @Override
    public void displayMenu() {
        int choice;
        Role role = null;
        String enteredUsername, enteredPassword, newUsername, newPassword;
        Branch branch = null;

        //Staff should only be able to log into their own branch but if we implement that in the order management then we need to change here\
        //For now I leave it as this

        do {
            System.out.println("1: Login");
            System.out.println("2: Create an Account");
            System.out.println("3: Admin Access");
            System.out.println("4: Change Username/Password");
            System.out.println("5: Exit");

            choice = InputHelper.getValidatedInt("Select Option: ", 1, 5);

            switch(choice){
                case 1:
                    while(true) {

                        branch = getBranch();

                        if(branch == null) {
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
                                    return;

                                case MANAGER:
                                    managerUI.setBranch(branch);
                                    managerUI.displayMenu();
                                    return;
                            }
                            break;

                        } else {
                            System.out.println("Incorrect Username or Password");
                        }
                    }
                case 2:
                    //check branch selected for unassigned objects
                    branch = getBranch();

                    if(branch == null) {
                        System.out.println("Branch doesn't exist");
                        continue;
                    }

                    while(true) {
                        String roleName = InputHelper.getValidatedString("Enter your role: ");

                        if (roleName.equalsIgnoreCase("STAFF")) {
                            role = Role.STAFF;
                            break;
                        } else if (roleName.equalsIgnoreCase("MANAGER")) {
                            role = Role.MANAGER;
                            break;
                        } else {
                            System.out.println("Invalid Role");
                        }
                    }

                    System.out.println("Searching for available staff accounts in selected branch");
                    Staff staff = loginController.getAvailableAccount(branch, role);

                    if(staff == null) {
                        System.out.println("No available accounts");
                        break;
                    }

                    //if have then let them claim one and change the password and username
                    newUsername = InputHelper.getValidatedString("Enter new Username: ");
                    newPassword = InputHelper.getValidatedString("Enter new Password: ");

                    if(loginController.setAccountDetails(branch, staff.getUsername(), newUsername, newPassword)) {
                        loginController.claimAccount(branch, staff);
                        System.out.println("Account Creation Successful");
                    } else {
                        System.out.println("Account Creation Unsuccessful");
                    }

                    break;

                case 3:

                    while(true) {
                        enteredUsername = InputHelper.getValidatedString("Enter username: ");
                        enteredPassword = InputHelper.getValidatedString("Enter password ");

                        if (enteredUsername.equals("Admin") && enteredPassword.equals("Admin")) {
                            adminUI.displayMenu();
                            break;
                        } else {
                            System.out.println("Incorrect Username or Password");
                        }
                    }

                    break;

                case 4:
                    branch = getBranch();

                    if(branch == null) {
                        System.out.println("Branch doesn't exist");
                        continue;
                    }

                    //login to retrieve staff object
                    enteredUsername = InputHelper.getValidatedString("Enter username: ");
                    enteredPassword = InputHelper.getValidatedString("Enter password ");
                    if (loginController.validateAccount(branch, enteredUsername, enteredPassword)) {
                        newUsername = InputHelper.getValidatedString("Enter new Username: ");
                        newPassword = InputHelper.getValidatedString("Enter new Password: ");

                        //double confirm if u wish to change
                        int confirm = InputHelper.getValidatedInt("Do you wish to change? Yes: 1, No: 0", 0 , 1);
                        if(confirm == 1) {
                            //change username and password
                            //reuse implementation for create an account
                            if(loginController.setAccountDetails(branch, enteredUsername, newUsername, newPassword)) {
                                System.out.println("Successful");
                            } else {
                                System.out.println("Unsuccessful");
                            }

                            break;

                        } else {
                            break;
                        }
                    }

                case 5:
                    System.out.println("Returning back to main menu");
            }
        } while (choice < 5);
    }

    private Branch getBranch() {
        String branchName = InputHelper.getValidatedString("Enter Branch Name to Access: ");
        return allBranches.getBranchByName(branchName);
    }
}
