package Boundary;

import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Display.IDisplayMenu;

public class AdminUI implements IDisplayMenu {

    private final IAllBranches allBranches;
    private final CreateBranchUI branchUI;
    private final  IDisplayMenu staffActionsUI;
    private final PaymentMethodUI paymentMethodUI;

    public AdminUI (IAllBranches allBranches, CreateBranchUI branchUI, IDisplayMenu staffActionsUI, PaymentMethodUI paymentMethodUI) {
        this.allBranches = allBranches;
        this.branchUI = branchUI;
        this.staffActionsUI = staffActionsUI;
        this.paymentMethodUI = paymentMethodUI;
    }

    @Override
    public void displayMenu() {
        int choice;


        System.out.println("Welcome Admin User");
        System.out.println("The following branches are currently active: ");
        //Display Branches
        for (int i = 1; i <= allBranches.getAllBranches().size(); i++) {
            System.out.println(i + ": " + allBranches.getAllBranches().get(i));
        }

//        System.out.println("Would you like to access the following branches or Open a new one");
//        System.out.println("1: Access Existing Branch");
//        System.out.println("2: Open a New Branch");
//        choice = InputHelper.getValidatedInt("Select 1 or 2: ", 1, 2);

        do {
            System.out.println("1: Staff Actions");
            System.out.println("2: Add or Remove Payment Methods");
            System.out.println("3: Open or Close a Branch");
            System.out.println("4: Exit");
            choice = InputHelper.getValidatedInt("Please select from the following:", 1, 8);

            switch(choice) {
                case 1:
                    staffActionsUI.displayMenu();
                    break;

                case 2:
                    paymentMethodUI.displayMenu();
                    break;

                case 3:
                    choice = InputHelper.getValidatedInt("1 to Open and 2 to Close", 1, 2);
                    if(choice == 1) {
                        branchUI.createBranch();
                    } else {
                        branchUI.closeBranch();
                    }
                    break;
            }

        } while (choice < 4);
    }
}
