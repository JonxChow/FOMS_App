package Controller;

import Entity.Menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private List<MenuItem> menuItems;

    public MenuController() {
        this.menuItems = new ArrayList<>();
    }

    // Method to add a new menu item
    public void addItem(MenuItem item) {
        menuItems.add(item);
        System.out.println("Menu item added successfully: " + item.getName());
    }

    // Method to remove a menu item
    public void removeItem(MenuItem item) {
        if (menuItems.remove(item)) {
            System.out.println("Menu item removed successfully: " + item.getName());
        } else {
            System.out.println("Failed to remove menu item: " + item.getName());
        }
    }

    // Method to edit an existing menu item
    public void editItem(MenuItem item) {
        int index = findItemIndex(item);
        if (index != -1) {
            menuItems.set(index, item);
            System.out.println("Menu item updated successfully: " + item.getName());
        } else {
            System.out.println("Menu item not found: " + item.getName());
        }
    }

    // Helper method to find an item's index
    private int findItemIndex(MenuItem item) {
        return menuItems.indexOf(item);
    }

    // Method to get the list of all menu items
    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems); // Return a copy of the list to avoid external modifications
    }
    public void displayMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("No items in the menu.");
            return;
        }
        for (MenuItem m : menuItems) {
            System.out.println("Name: " + m.getName() + ", Price: " + m.getPrice()
                    + ", Description: " + m.getDescription()
                    + ", Category: " + m.getCategory()
                    + ", Availability: " + (m.getAvailability() == 1 ? "Available" : "Not available"));
        }
    }
}

