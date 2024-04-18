import Boundary.AdminUI;
import Boundary.CreateBranchUI;
import Boundary.StaffActionsUI;
import Controller.NewBranchController;
import Controller.StaffManagementController;
import Entity.Lists.AllBranches;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;

public class FOMSApplication {

    public static void showUserInterface(IDisplayMenu userInterface) {
        userInterface.displayMenu();
    }

    public static void main(String[] args) {

        /*INIT CONTROLLERS and INTERFACE*/
        IAllBranches allBranches = new AllBranches();
        IStaffManager staffManager = new StaffManagementController();
        StaffActionsUI staffActionsUI = new StaffActionsUI(allBranches, staffManager);
        IBranchController branchManger = new NewBranchController(allBranches, staffActionsUI);
        CreateBranchUI branchUI = new CreateBranchUI(branchManger);


        IDisplayMenu adminUI = new AdminUI(allBranches, branchUI, staffActionsUI);
        //LoginUI
        //CustomerUI


        System.out.println("**********WELCOME TO FOMS APPLICATION************");
        System.out.println("****Continue As Customer Or Log In As a Staff****");
        int choice = InputHelper.getValidatedInt("Press 1 for Customer, 2 to Log in and 3 to Exit", 1, 3);
        switch (choice){
            case 1:
                //showUserInterface(CustomerUI);


            case 2:
                //showUserInterface(LoginUI);
                adminUI.displayMenu();

            case 3:
                //exit
        }

        /*List of methods to call when main screen has been implemented later*/
        //to create a branch: createBranchUI.createBranch();
        //Display admin menu: showUserInterface(adminUI);

    }
}