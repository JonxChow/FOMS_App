package Boundary;

import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Controllers.IBranchController;

/**
 * User interface class for creating and closing branches. This class provides methods to interact
 * with the user for inputting branch details, creating branches, adding staff, and closing branches.
 */
public class CreateBranchUI {
    private final IBranchController branchManager;

    /**
     * Constructs a CreateBranchUI with a dependency on the IBranchController.
     *
     * @param branchManager The branch controller used for creating and managing branches.
     */
    public CreateBranchUI(IBranchController branchManager) {
        this.branchManager = branchManager;
    }

    /**
     * Interacts with the user to gather input for creating a new branch, including its name and location,
     * and initializes it with staff and managers based on user input.
     */
    public void createBranch() {
        String name = InputHelper.getValidatedString("Enter Branch Name:");
        String location = InputHelper.getValidatedString("Enter Location Name:");

        Branch newBranch = branchManager.createBranch(name, location);
        if(newBranch == null) {
            System.out.println("Branch creation unsuccessful");
            return;
        }

        int numberOfStaff = InputHelper.getValidatedInt("Enter the number of staff to add: ", 1, 100);
        int numberOfManagers = (int) Math.ceil(numberOfStaff / 4.0);  // Ensuring there is one manager for every four staff

        branchManager.initializeBranchWithStaff(newBranch, numberOfStaff, numberOfManagers);

        System.out.println("Branch successfully created with staff and managers.");
    }

    /**
     * Interacts with the user to gather input for closing a branch based on its name.
     */
    public void closeBranch() {
        String name = InputHelper.getValidatedString("Enter Branch Name:");
        boolean success = branchManager.closeBranch(name);
        if (success) {
            System.out.println("Branch closed successfully.");
        } else {
            System.out.println("Failed to close the branch.");
        }
    }
}
