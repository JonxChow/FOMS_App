package Boundary;

import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Boundaries.getUserBranch;
import Interface.Display.IDisplayMenu;

public class ManagerUI implements IDisplayMenu, getUserBranch {

    private StaffActionsUI staffActionsUI;
    private MenuActionsUI menuActionsUI;
    public ManagerUI(StaffActionsUI staffActionsUI, MenuActionsUI menuActionsUI) {
        this.staffActionsUI = staffActionsUI;
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
                    staffActionsUI.displayMenu();
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
