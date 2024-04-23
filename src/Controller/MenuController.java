package Controller;

import Entity.Branch.Branch;
import Entity.Menu.MenuItem;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class MenuController {

    public MenuController() {
        // Now an empty constructor.
    }

    // Method to add a new menu item to a branch
    public void addItem(Branch branch, MenuItem item) {
        branch.getMenu().add(item);
        System.out.println("Menu item added successfully: " + item.getName());
    }

    // Method to remove a menu item from a branch
    public void removeItem(Branch branch, MenuItem item) {
        if (branch.getMenu().remove(item)) {
            System.out.println("Menu item removed successfully: " + item.getName());
        } else {
            System.out.println("Failed to remove menu item: " + item.getName());
        }
    }

    // Method to edit an existing menu item in a branch
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
