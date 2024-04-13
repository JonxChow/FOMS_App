package Controller;

import Entity.Branch.Branch;
import Entity.Lists.AllBranches;
import Interface.Admin.IBranchController;

public class NewBranchController implements IBranchController {

    private final AllBranches allBranches;
    private final StaffManagementController staffManager;

    public NewBranchController(AllBranches allBranches, StaffManagementController staffManager){
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
}
