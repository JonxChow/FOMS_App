package Interface.Boundaries;

import Entity.Branch.Branch;
import Interface.Display.IDisplayMenu;

/**
 * Interface that defines the methods for interacting with the menu user interface.
 */
public interface IMenuActionUI extends IDisplayMenu {

    /**
     * Displays the menu and its items.
     */
    void showMenu();

    /**
     * Adds a new menu item.
     */
    void addMenuItem();

    /**
     * Removes an existing menu item.
     */
    void removeMenuItem();

    /**
     * Edits an existing menu item.
     */
    void editMenuItem();

    /**
     * Sets the branch for which the menu actions will be performed.
     *
     * @param branch the branch object
     */
    void setBranch(Branch branch);
}