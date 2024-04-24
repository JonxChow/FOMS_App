package Controller;

import Boundary.StaffActionsUI;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;

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


    public Branch createBranch(String name, String location) {
        Branch newBranch = new Branch(name, location);
        allBranches.addBranch(newBranch);
        return newBranch;
    }


    public void intialiseBranchwithStaff(Branch branch) {
        //get createBranchUI to pass in no of staff and managers
        //call add staff in loop
        //call add managers in loop
        staffActionsUI.addStaff(branch);
    }


    public void closeBranch(String name) {
        allBranches.getAllBranches().remove(allBranches.getBranchByName(name));
    }
}
