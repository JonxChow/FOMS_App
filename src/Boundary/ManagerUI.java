package Boundary;

import Controller.StaffManagementController;
import Entity.Actor.Gender;
import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Boundaries.getUserBranch;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;

import java.util.ArrayList;

public class ManagerUI implements IDisplayMenu, getUserBranch {

    private IStaffManager staffManagementController;
    private MenuActionUI menuActionsUI;
    public ManagerUI(IStaffManager staffManagementController, MenuActionUI menuActionsUI) {
        this.staffManagementController = staffManagementController;
        this.menuActionsUI = menuActionsUI;
    }
    @Override
    public void displayMenu() {
        int choice;
        do{
            System.out.println("Welcome to Manager UI");
            System.out.println("Select from the following actions");
            System.out.println("1: Staff actions");
            System.out.println("2: Manage Menu");
            System.out.println("3: Display Staff List");
            System.out.println("4: Exit ");

            choice = InputHelper.getValidatedInt("Select your option: ", 1, 4);

            switch (choice)
            {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Exiting");
                    break;
            }
        } while(choice < 4);


    }

    @Override
    public void getUserBranch(Branch branch) {

    }
}
