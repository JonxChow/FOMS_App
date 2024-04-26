package Interface.Controllers;

import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Entity.Actor.Manager;

import java.util.ArrayList;

/**
 * Interface for managing staff operations within a branch.
 * This includes adding, and removing staff members and managers.
 */
public interface IStaffManager {

    /**
     * Attempts to add a staff member to a specified branch.
     *
     * @param branch The branch to which the staff member is to be added.
     * @param newStaff The new staff member to be added.
     * @return true if the staff member was successfully added, false otherwise.
     */
    boolean addStaff(Branch branch, Staff newStaff);

    /**
     * Attempts to add a manager to a specified branch.
     *
     * @param branch The branch to which the manager is to be added.
     * @param newManager The new manager to be added.
     * @return true if the manager was successfully added, false otherwise.
     */
    boolean addManager(Branch branch, Manager newManager);

    /**
     * Attempts to remove a staff member from a specified branch by their name.
     *
     * @param branch The branch from which the staff member is to be removed.
     * @param staffName The name of the staff member to be removed.
     * @return true if the staff member was successfully removed, false otherwise.
     */
    boolean removeStaff(Branch branch, String staffName);
}
