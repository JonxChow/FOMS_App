import Boundary.*;
import Controller.*;
import Entity.Lists.AllBranches;
import Helper.*;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IMenuActionUI;
import Interface.Boundaries.IPaymentMethodUI;
import Interface.Boundaries.IStaffDisplayUI;
import Interface.Boundaries.IStaffUI;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;

/**
 * This class represents the main application class for FOMS (Fast Order Management System).
 * It initializes various controllers, interfaces, and UI components, and manages the flow of the application.
 */
public class FOMSApplication {

    /**
     * The main method of the FOMS application.
     * It initializes necessary components, displays the main menu, and handles user interactions.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Deserialize existing branches data if available, otherwise create a new instance
        IAllBranches allBranches = (AllBranches) DataPersistence.deserialize("branches.dat");
        if (allBranches == null) {
            allBranches = new AllBranches();
        }

        // Initialize controllers and interfaces
        IStaffManager staffManager = new StaffManagementController();
        OrderController orderController = new OrderController();
        IStaffUI staffUI = new StaffUI(orderController);
        StaffActionsUI staffActionsUI = new StaffActionsUI(allBranches, staffManager);
        IMenuActionUI menuActionUI = new MenuActionUI();
        IBranchController branchManager = new BranchController(allBranches, staffActionsUI);
        ExcelBranchInitializer initializer = new ExcelBranchInitializer(branchManager, staffManager, allBranches);
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

        // Prompt user to initialize staff using a staff list file
        choice = InputHelper.getValidatedInt("Type 0 to continue to FOMS App \nType 1 to initialise staff using staff list: ", 0, 1);
        if(choice == 1){
            // Run code to read staff list from file
            initializer.initializeBranchesFromFile("./staff_list.xlsx");
        }

        // Main application loop
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

        } while(choice < 3);

        // Serialize allBranches after exit
        DataPersistence.serialize(allBranches, "branches.dat");
    }
}
