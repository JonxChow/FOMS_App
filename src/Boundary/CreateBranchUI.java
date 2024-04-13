package Boundary;

import Controller.NewBranchController;
import Entity.Branch.Branch;
import Helper.InputHelper;

public class CreateBranchUI {

    private final NewBranchController branchManager;

    public CreateBranchUI(NewBranchController branchManager) {
        this.branchManager = branchManager;
    }

    public void createBranch() {
        String name = InputHelper.getValidatedString("Enter Branch Name:");
        String location = InputHelper.getValidatedString("Enter Location Name:");

        Branch newBranch = branchManager.createBranch(name,location);

        int noOfStaff = InputHelper.getValidatedInt("Enter number of Staff: ", 1, 15);
        int noOfManagers = (int) (double) (noOfStaff / 4);

        System.out.println("Creating branch with " + noOfStaff + " Staff members and " + noOfManagers + " Managers");
        branchManager.intialiseBranchwithStaff(newBranch, noOfStaff, noOfManagers);

        System.out.println("Branch successfully created");
    }
}
