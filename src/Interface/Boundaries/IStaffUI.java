package Interface.Boundaries;

import Entity.Branch.Branch;
import Interface.Display.IDisplayMenu;

/**
 * Interface that defines the method for setting the branch in the staff user interface.
 */
public interface IStaffUI extends IDisplayMenu {

    /**
     * Sets the branch for which the staff-related operations will be performed.
     *
     * @param branch the branch object
     */
    void setBranch(Branch branch);
}