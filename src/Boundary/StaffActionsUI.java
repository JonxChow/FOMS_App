package Boundary;

import Controller.StaffPromotionController;
import Entity.Branch.Branch;
import Entity.Actor.Gender;
import Entity.Actor.Staff;
import Entity.Actor.Manager;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IStaffUI;
import Interface.Controllers.IStaffManager;
import Interface.Controllers.IStaffPromotion;
import Interface.Display.IDisplayMenu;

import java.util.Scanner;

public class StaffActionsUI implements IDisplayMenu, IStaffUI {
    private final IStaffManager staffManager;
    private final IAllBranches allBranches;
    private final IStaffPromotion staffPromotionController;
    private final Scanner scanner = new Scanner(System.in);

    public StaffActionsUI(IAllBranches allBranches, IStaffManager staffManager) {
        this.staffManager = staffManager;
        this.allBranches = allBranches;
        this.staffPromotionController = new StaffPromotionController();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n--- Staff Management Menu ---");
        System.out.println("1: Add Staff");
        System.out.println("2: Add Manager");
        System.out.println("3: Remove Staff/Manager");
        System.out.println("4: Promote a Staff");
        System.out.println("5: Transfer Manager");
        System.out.println("6: Exit");

        int choice;
        do {
            choice = InputHelper.getValidatedInt("Select Option: ", 1, 4);

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
                case 6:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice < 6);
    }

    public Branch getBranch() {
        String name = InputHelper.getValidatedString("Enter Branch Name: ");
        return allBranches.getBranchByName(name);
    }

    public void addStaffIndividual(Branch branch) {
        if (branch == null) {
            System.out.println("Branch not found.");
            return;
        }

        // Calculate needed managers
        int currentStaff = branch.getStaffMembers().size();
        int currentManagers = (int) branch.getStaffMembers().stream().filter(s -> s instanceof Manager).count();
        int requiredManagers = (currentStaff / 4) - currentManagers;

        String staffName = InputHelper.getValidatedString("Enter staff name: ");
        Gender gender = InputHelper.getValidatedInt("Enter staff gender 0(female), 1(male)", 0, 1) == 0 ? Gender.FEMALE : Gender.MALE;
        int staffAge = InputHelper.getValidatedInt("Enter staff age: ", 18, 65);
        Staff newStaff = new Staff(-1, staffName, staffAge, gender);

        if (requiredManagers > 0) {
            System.out.println("Additional manager required. Please add a manager first.");
            return;
        }

        if (staffManager.addStaff(branch, newStaff)) {
            System.out.println("Staff added successfully.");
        } else {
            System.out.println("Unable to add staff.");
        }
    }

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
}
