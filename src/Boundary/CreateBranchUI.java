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

        Branch newBranch = branchManager.createBranch(name,location);

        //get no of staff and managers to add and perform check
        branchManager.intialiseBranchwithStaff(newBranch);

        System.out.println("Branch successfully created");
    }

    public void closeBranch() {
        String name = InputHelper.getValidatedString("Enter Branch Name:");
        branchManager.closeBranch(name);
    }
}
