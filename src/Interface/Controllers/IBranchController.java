package Interface.Controllers;

import Entity.Branch.Branch;

public interface IBranchController {

    Branch createBranch(String name, String location);

    void intialiseBranchwithStaff(Branch branch, int noOfStaff, int noOfManagers);

    void closeBranch(String name);
}
