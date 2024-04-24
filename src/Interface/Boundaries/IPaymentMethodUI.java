package Interface.Boundaries;

import Entity.Branch.Branch;
import Interface.Display.IDisplayMenu;

public interface IPaymentMethodUI extends IDisplayMenu {

    void showCurrentPaymentMethods(Branch branch);
}
