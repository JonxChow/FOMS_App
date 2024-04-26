package Interface.Boundaries;

import Entity.Branch.Branch;

/**
 * Interface that defines the methods for staff-related actions in the user interface.
 */
public interface IStaffActionsUI {

    /**
     * Returns the branch associated with the staff actions.
     *
     * @return the branch object
     */
    Branch getBranch();

    /**
     * Adds an individual staff member to the specified branch.
     *
     * @param branch the branch to which the staff member should be added
     */
    void addStaffIndividual(Branch branch);

    /**
     * Adds an individual manager to the specified branch.
     *
     * @param branch the branch to which the manager should be added
     */
    void addManagerIndividual(Branch branch);

    /**
     * Removes a staff member from the associated branch.
     */
    void removeStaff();
}