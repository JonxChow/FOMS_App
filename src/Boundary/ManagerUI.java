package Boundary;

import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Boundaries.IMenuActionUI;
import Interface.Boundaries.IStaffDisplayUI;
import Interface.Boundaries.IStaffUI;
import Interface.Display.IDisplayMenu;

public class ManagerUI implements IDisplayMenu {

    private IStaffUI staffUI;
    private IMenuActionUI menuActionsUI;
    private final IStaffDisplayUI staffDisplayUI;
    private Branch branch;

    public ManagerUI(IStaffUI staffUI, IMenuActionUI menuActionsUI, IStaffDisplayUI staffDisplayUI) {
        this.staffUI = staffUI;
        this.menuActionsUI = menuActionsUI;
        this.staffDisplayUI = staffDisplayUI;
    }
    @Override
    public void displayMenu() {
        int choice;
        do{
            System.out.println("Welcome to Manager UI");
            System.out.println("Select from the following actions");
            System.out.println("1: Process Orders");
            System.out.println("2: Manage Menu");
            System.out.println("3: Display Staff List");
            System.out.println("4: Exit ");

            choice = InputHelper.getValidatedInt("Select your option: ", 1, 4);

            switch (choice)
            {
                case 1:
                    staffUI.setBranch(this.branch);
                    staffUI.displayMenu();
                    break;
                case 2:
                    menuActionsUI.setBranch(this.branch);
                    menuActionsUI.displayMenu();
                    break;
                case 3:
                    staffDisplayUI.displayForManager(this.branch);
                    break;
                case 4:
                    System.out.println("Exiting");
                    break;
            }
        } while(choice < 4);
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
