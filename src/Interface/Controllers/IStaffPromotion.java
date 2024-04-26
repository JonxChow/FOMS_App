package Interface.Controllers;

import Entity.Branch.Branch;

/**
 * This interface defines methods for staff promotion and manager transfer.
 */
public interface IStaffPromotion {

    /**
     * Promotes a staff member at the specified branch.
     *
     * @param branch The branch where the staff member works.
     * @param name   The name of the staff member to be promoted.
     */
    void promoteStaff(Branch branch, String name);

    /**
     * Transfers a manager from one branch to another.
     *
     * @param staffManager  The staff manager to be transferred.
     * @param branch        The current branch of the staff manager.
     * @param targetBranch  The branch to which the staff manager will be transferred.
     */
    void transferManager(IStaffManager staffManager, Branch branch, Branch targetBranch);
}
