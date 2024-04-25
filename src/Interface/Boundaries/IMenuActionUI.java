package Interface.Boundaries;

import Entity.Branch.Branch;
import Interface.Display.IDisplayMenu;

public interface IMenuActionUI extends IDisplayMenu {

    void showMenu();
    void addMenuItem();
    void removeMenuItem();
    void editMenuItem();
    void setBranch(Branch branch);
}
