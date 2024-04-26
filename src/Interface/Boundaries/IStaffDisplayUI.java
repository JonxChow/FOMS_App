package Interface.Boundaries;

import Entity.Branch.Branch;

/**
 * Interface that defines the methods for displaying staff information in the user interface.
 */
public interface IStaffDisplayUI {

    /**
     * Displays the staff information for a specific branch, intended for managers.
     *
     * @param branch the branch for which the staff information should be displayed
     */
    void displayForManager(Branch branch);

    /**
     * Displays the staff information for all branches, intended for administrators.
     */
    void displayForAdmin();
}