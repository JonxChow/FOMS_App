package Boundary;

import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IPaymentMethodUI;
import Interface.Boundaries.IStaffDisplayUI;
import Interface.Display.IDisplayMenu;

public class AdminUI implements IDisplayMenu {

    private final IAllBranches allBranches;
    private final CreateBranchUI branchUI;
    private final  IDisplayMenu staffActionsUI;
    private final IPaymentMethodUI paymentMethodUI;
    private final IStaffDisplayUI staffDisplayUI;

    public AdminUI (IAllBranches allBranches, CreateBranchUI branchUI, IDisplayMenu staffActionsUI, IPaymentMethodUI paymentMethodUI, IStaffDisplayUI staffDisplayUI) {
        this.allBranches = allBranches;
        this.branchUI = branchUI;
        this.staffActionsUI = staffActionsUI;
        this.paymentMethodUI = paymentMethodUI;
        this.staffDisplayUI = staffDisplayUI;
    }

    @Override
    public void displayMenu() {
        int choice;

        do {

            System.out.println("Welcome Admin User");
            System.out.println("The following branches are currently active: ");
            //Display Branches
            allBranches.printBranches();
            //add in no branches open

            System.out.println("1: Staff Actions");
            System.out.println("2: Add or Remove Payment Methods");
            System.out.println("3: Display Staff Lists");
            System.out.println("4: Open or Close a Branch");
            System.out.println("5: Exit");
            choice = InputHelper.getValidatedInt("Please select from the following:", 1, 5);

            switch(choice) {
                case 1:
                    staffActionsUI.displayMenu();
                    break;

                case 2:
                    paymentMethodUI.displayMenu();
                    break;

                case 3:
                    staffDisplayUI.displayForAdmin();
                    break;

                case 4:
                    choice = InputHelper.getValidatedInt("1 to Open and 2 to Close", 1, 2);
                    if(choice == 1) {
                        branchUI.createBranch();
                    } else {
                        branchUI.closeBranch();
                    }
                    break;
            }

        } while (choice < 5);
    }
}
