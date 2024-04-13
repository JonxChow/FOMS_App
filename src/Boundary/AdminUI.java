package Boundary;

import Entity.Actor.Admin;
import Entity.Branch.Branch;
import Entity.Lists.AllBranches;
import Helper.InputHelper;
import Interface.Display.IDisplayMenu;

import java.awt.desktop.SystemEventListener;

public class AdminUI implements IDisplayMenu {

    private final AllBranches allBranches;

    public AdminUI (AllBranches allBranches) {
        this.allBranches = allBranches;
    }


    @Override
    public void displayMenu() {
        System.out.println("Welcome Admin User");
        System.out.println("The following branches are currently active: ");
        //Display Branches

        System.out.println("Would you like to access the following branches or Open a new one");

        int choice = InputHelper.getValidatedInt("Please select from the following:", 1, 8);
        System.out.println("1: Add, Edit or Remove Staff Accounts");
        System.out.println("2: Display Staff List");
        System.out.println("3: Assign Managers to a Branch");
        System.out.println("4: Promote Staff");
        System.out.println("5: Transfer Staff or Manager");
        System.out.println("6: Add or Remove Payment Methods");
        System.out.println("7: Open or Close a Branch");
        System.out.println("8: Exit");

        switch(choice) {
            case 1:


            case 2:

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            case 7:
                break;

            case 8:
                break;
        }
    }
}
