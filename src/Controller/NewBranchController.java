package Controller;

import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;

public class NewBranchController implements IBranchController {

    private final IAllBranches allBranches;
    private final IStaffManager staffManager;

    public NewBranchController(IAllBranches allBranches, IStaffManager staffManager){
        this.allBranches = allBranches;
        this.staffManager = staffManager;
    }


    @Override
    public Branch createBranch(String name, String location) {
        Branch newBranch = new Branch(name, location);
        allBranches.addBranch(newBranch);
        return newBranch;
    }

    @Override
    public void intialiseBranchwithStaff(Branch branch, int noOfStaff, int noOfManagers) {
        staffManager.addStaff(branch, noOfStaff);
        staffManager.addManager(branch, noOfManagers);
    }

    @Override
    public void closeBranch(String name) {
        allBranches.getAllBranches().remove(allBranches.getBranchByName(name));
    }
}
