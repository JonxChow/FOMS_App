package Boundary;

import Controller.StaffPromotionController;
import Entity.Branch.Branch;
import Entity.Actor.Gender;
import Entity.Actor.Staff;
import Entity.Actor.Manager;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IStaffActionsUI;
import Interface.Controllers.IStaffManager;
import Interface.Controllers.IStaffPromotion;
import Interface.Display.IDisplayMenu;

import java.util.Scanner;

/**
 * Provides a user interface for managing staff and managers within different branches.
 */
public class StaffActionsUI implements IDisplayMenu, IStaffActionsUI {
    private final IStaffManager staffManager;
    private final IAllBranches allBranches;
    private final IStaffPromotion staffPromotionController;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a StaffActionsUI object with the provided IAllBranches and IStaffManager implementations.
     * @param allBranches The interface providing access to all branches.
     * @param staffManager The interface providing staff and manager management functionalities.
     */
    public StaffActionsUI(IAllBranches allBranches, IStaffManager staffManager) {
        this.staffManager = staffManager;
        this.allBranches = allBranches;
        this.staffPromotionController = new StaffPromotionController();
    }

    /**
     * Displays the menu for managing staff and managers and handles user interactions.
     */
    @Override
    public void displayMenu() {

        int choice;
        do {
            System.out.println("\n--- Staff Management Menu ---");
            System.out.println("1: Add Staff");
            System.out.println("2: Add Manager");
            System.out.println("3: Remove Staff/Manager");
            System.out.println("4: Promote a Staff");
            System.out.println("5: Transfer Manager");
            System.out.println("6: Exit");
            choice = InputHelper.getValidatedInt("Select Option: ", 1, 6);

            switch (choice) {
                case 1:
                    addStaffIndividual(getBranch());
                    break;
                case 2:
                    addManagerIndividual(getBranch());
                    break;
                case 3:
                    removeStaff();
                    break;
                case 4:
                    Branch branch = getBranch();
                    String name = InputHelper.getValidatedString("Enter Staff name: ");
                    staffPromotionController.promoteStaff(branch, name);
                    break;
                case 5:
                    //Display branches here
                    System.out.println("Input Name of Branch to select manager below");
                    Branch sourceBranch = getBranch();
                    System.out.println("Input Name of Branch to transfer manager to below");
                    Branch targetBranch = getBranch();
                    staffPromotionController.transferManager(this.staffManager, sourceBranch, targetBranch);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice < 6);
    }

    /**
     * Retrieves the branch for staff and manager management based on user input.
     * @return The branch selected by the user.
     */
    public Branch getBranch() {
        String name = InputHelper.getValidatedString("Enter Branch Name: ");
        return allBranches.getBranchByName(name);
    }

    /**
     * Adds a new staff member to the specified branch.
     * @param branch The branch to which the staff member will be added.
     */
    public void addStaffIndividual(Branch branch) {
        if (branch == null) {
            System.out.println("Branch not found.");
            return;
        }

        // Calculate needed managers
        // Calculate the number of managers needed after adding one more staff
        int requiredManagers = calculateManagersNeeded(branch.getNoOfStaff() + 1);

        // Check if the current number of managers meets the requirement
        if (requiredManagers > branch.getNoOfManager()) {
            System.out.println(requiredManagers + " managers needed, only " + branch.getNoOfManager() + " present.");
            System.out.println("Additional manager required. Please add a manager first.");
            return;
        }

        String staffName = InputHelper.getValidatedString("Enter staff name: ");
        Gender gender = InputHelper.getValidatedInt("Enter staff gender 0(female), 1(male)", 0, 1) == 0 ? Gender.FEMALE : Gender.MALE;
        int staffAge = InputHelper.getValidatedInt("Enter staff age: ", 18, 65);
        Staff newStaff = new Staff(-1, staffName, staffAge, gender);


        if (staffManager.addStaff(branch, newStaff)) {
            System.out.println("Staff added successfully.");
        } else {
            System.out.println("Unable to add staff.");
        }
    }

    /**
     * Adds a new manager to the specified branch.
     * @param branch The branch to which the manager will be added.
     */
    public void addManagerIndividual(Branch branch) {
        if (branch == null) {
            System.out.println("Branch not found.");
            return;
        }

        String managerName = InputHelper.getValidatedString("Enter manager name: ");
        Gender gender = InputHelper.getValidatedInt("Enter manager gender 0(female), 1(male)", 0, 1) == 0 ? Gender.FEMALE : Gender.MALE;
        int managerAge = InputHelper.getValidatedInt("Enter manager age: ", 18, 65);
        Manager newManager = new Manager(-1, managerName, managerAge, gender);

        if (staffManager.addManager(branch, newManager)) {
            System.out.println("Manager added successfully.");
        } else {
            System.out.println("Unable to add manager.");
        }
    }

    /**
     * Removes a staff member or manager from the specified branch.
     */
    public void removeStaff() {
        String name = InputHelper.getValidatedString("Enter Branch Name: ");
        Branch branch = allBranches.getBranchByName(name);
        String staffName = InputHelper.getValidatedString("Enter Name of Staff/Manager to remove: ");

        if (staffManager.removeStaff(branch, staffName)) {
            System.out.println("Staff/Manager removed successfully.");
        } else {
            System.out.println("Unable to remove staff/manager.");
        }
    }

    /**
     * Calculates the number of managers needed based on the total number of staff.
     * @param noOfStaff The total number of staff.
     * @return The number of managers needed.
     */
    private int calculateManagersNeeded(int noOfStaff) {
        if (noOfStaff <= 4) {
            return 1;
        } else if (noOfStaff <= 8) {
            return 2;
        } else {
            return 3; // For 9-15 staff members
        }
    }
}
