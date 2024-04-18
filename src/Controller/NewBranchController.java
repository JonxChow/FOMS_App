package Controller;

import Boundary.StaffActionsUI;
import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;

import java.util.ArrayList;

public class NewBranchController implements IBranchController {

    private final IAllBranches allBranches;
    //private final IStaffManager staffManager;
    private final StaffActionsUI staffActionsUI;

    public NewBranchController(IAllBranches allBranches, StaffActionsUI staffActionsUI){
        this.allBranches = allBranches;
        //this.staffManager = staffManager;
        this.staffActionsUI = staffActionsUI;
    }


    @Override
    public Branch createBranch(String name, String location) {
        Branch newBranch = new Branch(name, location);
        allBranches.addBranch(newBranch);
        return newBranch;
    }

    @Override
    public void intialiseBranchwithStaff(Branch branch) {
        //divert to some UI to get staff info
        staffActionsUI.addStaff(branch);
    }

    @Override
    public void closeBranch(String name) {
        allBranches.getAllBranches().remove(allBranches.getBranchByName(name));
    }
}
