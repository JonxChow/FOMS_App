package Interface.Boundaries;

import Entity.Branch.Branch;
import Interface.Display.IDisplayMenu;

/**
 * Interface that defines the method for displaying payment methods in the user interface.
 */
public interface IPaymentMethodUI extends IDisplayMenu {

    /**
     * Displays the current payment methods available for the given branch.
     *
     * @param branch the branch for which the payment methods should be displayed
     */
    void showCurrentPaymentMethods(Branch branch);
}