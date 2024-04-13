package Interface.Admin;

import Entity.Branch.Branch;

public interface IBranchController {

    Branch createBranch(String name, String location);

    void intialiseBranchwithStaff(Branch branch, int noOfStaff, int noOfManagers);
}
