package Controller;

import Entity.Branch.Branch;
import Entity.Menu.MenuItem;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Controls the management of menu items within a branch's menu.
 * This includes adding, removing, editing, and displaying menu items.
 */
public class MenuController {

    /**
     * Constructs a MenuController instance. This constructor is empty
     * as all methods are operational upon the menu associated with a branch.
     */
    public MenuController() {
        // Now an empty constructor.
    }

    /**
     * Adds a new menu item to a specified branch's menu.
     *
     * @param branch The branch where the menu item will be added.
     * @param item The menu item to add.
     */
    public void addItem(Branch branch, MenuItem item) {
        branch.getMenu().add(item);
        System.out.println("Menu item added successfully: " + item.getName());
    }

    /**
     * Removes a menu item from a specified branch's menu.
     *
     * @param branch The branch from which the menu item will be removed.
     * @param item The menu item to remove.
     */
    public void removeItem(Branch branch, MenuItem item) {
        if (branch.getMenu().remove(item)) {
            System.out.println("Menu item removed successfully: " + item.getName());
        } else {
            System.out.println("Failed to remove menu item: " + item.getName());
        }
    }

    /**
     * Edits an existing menu item in a specified branch's menu. The method finds
     * an item by name, removes it, and replaces it with the updated version.
     *
     * @param branch The branch where the menu item will be edited.
     * @param newItem The new menu item that will replace the old one.
     */
    public void editItem(Branch branch, MenuItem newItem) {
        Iterator<MenuItem> iterator = branch.getMenu().iterator();
        boolean foundAndRemoved = false;
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            if (item.getName().equals(newItem.getName())) {
                iterator.remove();
                foundAndRemoved = true;
                break; // Assuming names are unique
            }
        }

        if (foundAndRemoved) {
            branch.getMenu().add(newItem);
            System.out.println("New menu item added successfully: " + newItem.getName());
        } else {
            System.out.println("Menu item with name '" + newItem.getName() + "' not found.");
        }
    }

    /**
     * Displays all the menu items currently available in a specified branch's menu.
     *
     * @param branch The branch whose menu will be displayed.
     */
    public void displayMenu(Branch branch) {
        if (branch.getMenu().isEmpty()) {
            System.out.println("No items in the menu.");
            return;
        }
        for (MenuItem m : branch.getMenu()) {
            System.out.println("Name: " + m.getName() + ", Price: " + m.getPrice()
                    + ", Description: " + m.getDescription()
                    + ", Category: " + m.getCategory()
                    + ", Availability: " + (m.getAvailability() == 1 ? "Available" : "Not available"));
        }
    }
}
