package Controller;

import Boundary.StaffActionsUI;
import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IStaffUI;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;

public class NewBranchController implements IBranchController {
    private final IAllBranches allBranches;
    private final IStaffUI staffActionsUI;

    public NewBranchController(IAllBranches allBranches, IStaffUI staffActionsUI){
        this.allBranches = allBranches;
        this.staffActionsUI = staffActionsUI;
    }

    public Branch createBranch(String name, String location) {
        Branch newBranch = new Branch(name, location);
        allBranches.addBranch(newBranch);
        return newBranch;
    }

    public void initializeBranchWithStaff(Branch branch, int numberOfStaff, int numberOfManagers) {
        // Adding staff to the branch while respecting the staff to manager ratio
        for (int i = 0; i < numberOfStaff; i++) {
            staffActionsUI.addStaffIndividual(branch);
        }
        for (int i = 0; i < numberOfManagers; i++) {
            staffActionsUI.addManagerIndividual(branch);
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
