package Interface.Controllers;

import Entity.Branch.Branch;

public interface IBranchController {

    Branch createBranch(String name, String location);

    public void initializeBranchWithStaff(Branch branch, int numberOfStaff, int numberOfManagers);

    boolean closeBranch(String name);
}
