package Interface.Controllers;

import Entity.Branch.Branch;

public interface IStaffPromotion {
    void promoteStaff(Branch branch, String name);
    void transferManager(IStaffManager staffManager, Branch branch, Branch targetBranch);
}
