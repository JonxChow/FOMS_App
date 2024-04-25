package Controller;

import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IStaffActionsUI;
import Interface.Controllers.IBranchController;

public class BranchController implements IBranchController {
    private final IAllBranches allBranches;
    private final IStaffActionsUI staffActionsUI;

    public BranchController(IAllBranches allBranches, IStaffActionsUI staffActionsUI){
        this.allBranches = allBranches;
        this.staffActionsUI = staffActionsUI;
    }

    public Branch createBranch(String name, String location) {
        if(allBranches.branchExists(name)) {
            System.out.println("Branch with this name already exists");
            return null;
        }
        Branch newBranch = new Branch(name, location);
        allBranches.addBranch(newBranch);
        return newBranch;
    }

    public void initializeBranchWithStaff(Branch branch, int numberOfStaff, int numberOfManagers) {
        // Adding staff to the branch while respecting the staff to manager ratio
        for (int i = 0; i < numberOfManagers; i++) {
            staffActionsUI.addManagerIndividual(branch);
        }

        for (int i = 0; i < numberOfStaff; i++) {
            staffActionsUI.addStaffIndividual(branch);
        }
    }

    public boolean closeBranch(String name) {
        Branch branch = allBranches.getBranchByName(name);
        if (branch != null) {
            allBranches.removeBranch(branch);
            return true;
        }
        return false;
    }
}
