package Interface.Controllers;

import Entity.Branch.Branch;

/**
 * Interface that defines the methods for controlling branch-related operations.
 */
public interface IBranchController {

    /**
     * Creates a new branch with the specified name and location.
     *
     * @param name     the name of the branch
     * @param location the location of the branch
     * @return the created branch object
     */
    Branch createBranch(String name, String location);

    /**
     * Initializes the specified branch with a given number of staff and managers.
     *
     * @param branch          the branch to be initialized
     * @param numberOfStaff   the number of staff to be added
     * @param numberOfManagers the number of managers to be added
     */
    void initializeBranchWithStaff(Branch branch, int numberOfStaff, int numberOfManagers);

    /**
     * Closes the branch with the specified name.
     *
     * @param name the name of the branch to be closed
     * @return true if the branch was successfully closed, false otherwise
     */
    boolean closeBranch(String name);
}