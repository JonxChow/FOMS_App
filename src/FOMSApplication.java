import Boundary.*;
import Controller.*;
import Entity.Lists.AllBranches;
import Helper.DataPersistence;
import Helper.ExcelBranchInitializer;
import Helper.InputHelper;
import Helper.StaffDisplayUI;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IMenuActionUI;
import Interface.Boundaries.IPaymentMethodUI;
import Interface.Boundaries.IStaffDisplayUI;
import Interface.Boundaries.IStaffUI;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;

public class FOMSApplication {

    public static void main(String[] args) {
        IAllBranches allBranches = (AllBranches) DataPersistence.deserialize("branches.dat");
        if (allBranches == null) {
            allBranches = new AllBranches();
        }

        /*INIT CONTROLLERS and INTERFACE*/
        //IAllBranches allBranches = new AllBranches();
        IStaffManager staffManager = new StaffManagementController();
        OrderController orderController = new OrderController();
        IStaffUI staffUI = new StaffUI(orderController);
        StaffActionsUI staffActionsUI = new StaffActionsUI(allBranches, staffManager);
        IMenuActionUI menuActionUI = new MenuActionUI();
        IBranchController branchManager = new BranchController(allBranches, staffActionsUI);
        ExcelBranchInitializer initializer = new ExcelBranchInitializer(branchManager, staffManager);
        CreateBranchUI branchUI = new CreateBranchUI(branchManager);
        IStaffDisplayUI staffDisplayUI = new StaffDisplayUI(allBranches);
        PaymentController paymentController = new PaymentController();
        IPaymentMethodUI paymentMethodUI = new PaymentMethodUI(paymentController, allBranches);
        AdminUI adminUI = new AdminUI(allBranches, branchUI, staffActionsUI, paymentMethodUI, staffDisplayUI);
        ManagerUI managerUI = new ManagerUI(staffUI, menuActionUI, staffDisplayUI);
        LoginController loginController = new LoginController();
        LoginUI loginUi = new LoginUI(allBranches, loginController, staffUI, managerUI, adminUI);
        IDisplayMenu customerUI = new CustomerUI(allBranches, orderController, paymentController, paymentMethodUI);

        int choice;

        choice = InputHelper.getValidatedInt("Type 0 to continue to FOMS App \n Type 1 to initialise staff using staff list: ", 0, 1);
        if(choice == 1){
            //run code to read
            initializer.initializeBranchesFromFile("./staff_list.xlsx");
        }

        do {
            System.out.println("**********WELCOME TO FOMS APPLICATION************");
            System.out.println("****Continue As Customer Or Log In As a Staff****");
            choice = InputHelper.getValidatedInt("Press 1 for Customer, 2 to Log in and 3 to Exit", 1, 3);
            switch (choice) {
                case 1:
                    customerUI.displayMenu();
                    break;

                case 2:
                    loginUi.displayMenu();
                    break;
                case 3:
                    System.out.println("Thank you for using FOMS App!");
                    System.out.println("Program terminating");
            }

            /*List of methods to call when main screen has been implemented later*/
            //to create a branch: createBranchUI.createBranch();
            //Display admin menu: showUserInterface(adminUI);
        } while(choice < 3);

        //Serialize allBranches after exit
        DataPersistence.serialize(allBranches, "branches.dat");
    }
}