import Boundary.*;
import Controller.*;
import Entity.Branch.Branch;
import Entity.Lists.AllBranches;
import Entity.Order.Order;
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
        OrderController orderController = new OrderController();
        StaffUI staffUI = new StaffUI(orderController);
        StaffActionsUI staffActionsUI = new StaffActionsUI(allBranches, staffManager);
        IDisplayMenu menuActionUI = new MenuActionUI();
        //IBranchController branchManger = new NewBranchController(allBranches, staffActionsUI);
        //CreateBranchUI branchUI = new CreateBranchUI(branchManger);
        //AdminUI adminUI = new AdminUI(allBranches, branchUI, staffActionsUI);
        ManagerUI managerUI = new ManagerUI(staffActionsUI, menuActionUI);
        LoginController loginController = new LoginController();
        //LoginUI loginUi = new LoginUI(allBranches, loginController, staffUI, managerUI, adminUI);
        PaymentController paymentController = new PaymentController();
        //IDisplayMenu customerUI = new CustomerUI(allBranches, orderController, paymentController);

        int choice;

        do {
            System.out.println("**********WELCOME TO FOMS APPLICATION************");
            System.out.println("****Continue As Customer Or Log In As a Staff****");
            choice = InputHelper.getValidatedInt("Press 1 for Customer, 2 to Log in and 3 to Exit", 1, 3);
            switch (choice) {
                case 1:
                    //customerUI.displayMenu();
                    break;

                case 2:
                    //loginUi.displayMenu();
                    break;

                case 3:
                    System.out.println("Program terminating");
            }

            /*List of methods to call when main screen has been implemented later*/
            //to create a branch: createBranchUI.createBranch();
            //Display admin menu: showUserInterface(adminUI);
        } while(choice < 3);
    }
}