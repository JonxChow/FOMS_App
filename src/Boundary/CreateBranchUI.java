package Boundary;

import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Controllers.IBranchController;

public class CreateBranchUI {
    private final IBranchController branchManager;

    public CreateBranchUI(IBranchController branchManager) {
        this.branchManager = branchManager;
    }

    public void createBranch() {
        String name = InputHelper.getValidatedString("Enter Branch Name:");
        String location = InputHelper.getValidatedString("Enter Location Name:");

        Branch newBranch = branchManager.createBranch(name, location);

        int numberOfStaff = InputHelper.getValidatedInt("Enter the number of staff to add: ", 1, 100);
        int numberOfManagers = (int) Math.ceil(numberOfStaff / 4.0);  // Ensuring there is one manager for every four staff

        branchManager.initializeBranchWithStaff(newBranch, numberOfStaff, numberOfManagers);

        System.out.println("Branch successfully created with staff and managers.");
    }

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
