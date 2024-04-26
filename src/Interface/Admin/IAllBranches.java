package Interface.Admin;

import Entity.Actor.Staff;
import Entity.Branch.Branch;

import java.util.List;

/**
 * Interface that defines the methods for managing branches and staff.
 */
public interface IAllBranches {

    /**
     * Adds a new branch to the system.
     *
     * @param branch the branch to be added
     */
    void addBranch(Branch branch);

    /**
     * Retrieves a list of all branches in the system.
     *
     * @return a list of all branches
     */
    List<Branch> getAllBranches();

    /**
     * Retrieves a branch by its name.
     *
     * @param name the name of the branch
     * @return the branch with the specified name, or null if not found
     */
    Branch getBranchByName(String name);

    /**
     * Removes a branch from the system.
     *
     * @param branch the branch to be removed
     */
    void removeBranch(Branch branch);

    /**
     * Retrieves a list of all staff members across all branches.
     *
     * @return a list of all staff members
     */
    List<Staff> getAllStaff();

    /**
     * Prints the details of all branches in the system.
     */
    void printBranches();

    /**
     * Checks if a branch with the specified name exists in the system.
     *
     * @param branchName the name of the branch
     * @return true if the branch exists, false otherwise
     */
    boolean branchExists(String branchName);
}