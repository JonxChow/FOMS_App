package Boundary;

import Controller.MenuController;
import Entity.Branch.Branch;
import Entity.Menu.Menu;
import Entity.Menu.MenuItem;
import Helper.InputHelper;
import Interface.Display.IDisplayMenu;

import java.util.List;

import java.util.Scanner;

public class MenuActionUI {
    private MenuController menuController;
    private Scanner scanner;

    public MenuActionUI(MenuController menuController) {
        this.menuController = menuController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Add Menu Item");
        System.out.println("2. Remove Menu Item");
        System.out.println("3. Edit Menu Item");
        System.out.println("4. Display Menu");
        System.out.println("5. Exit");
        int choice = InputHelper.getValidatedInt("Choose an option: ", 1, 5);

        switch (choice) {
            case 1:
                addMenuItem();
                break;
            case 2:
                removeMenuItem();
                break;
            case 3:
                editMenuItem();
                break;
            case 4:
                displayMenu();
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
        }
    }

    public void displayMenu() {
        menuController.displayMenu();
    }

    public void addMenuItem() {
        String name = InputHelper.getValidatedString("Enter the name of the new menu item:");
        double price = Double.parseDouble(InputHelper.getValidatedString("Enter the price of the new menu item:"));
        String description = InputHelper.getValidatedString("Enter the description of the new menu item:");
        String category = InputHelper.getValidatedString("Enter the category of the new menu item:");
        int availability = InputHelper.getValidatedInt("Enter availability (1 for available, 0 for not available):", 0, 1);

        MenuItem newItem = new MenuItem(name, price, description, category, availability);
        menuController.addItem(newItem);
    }

    public void removeMenuItem() {
        String name = InputHelper.getValidatedString("Enter the name of the menu item you want to remove:");

        MenuItem item = menuController.getMenuItems().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (item != null) {
            menuController.removeItem(item);
        } else {
            System.out.println("Menu item not found.");
        }
    }

    public void editMenuItem() {
        String name = InputHelper.getValidatedString("Enter the name of the menu item you want to edit:");

        MenuItem itemToEdit = menuController.getMenuItems().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (itemToEdit != null) {
            String newName = InputHelper.getValidatedString("Enter new name (leave blank to keep current):");
            if (!newName.isEmpty()) itemToEdit.setName(newName);

            String newPrice = InputHelper.getValidatedString("Enter new name (leave blank to keep current):");
            if (!newPrice.isEmpty()) itemToEdit.setPrice(Double.parseDouble(newPrice));

            String newDescription = InputHelper.getValidatedString("Enter new description (leave blank to keep current):");
            if (!newDescription.isEmpty()) itemToEdit.setDescription(newDescription);

            String newCategory = InputHelper.getValidatedString("Enter new category (leave blank to keep current):");
            if (!newCategory.isEmpty()) itemToEdit.setCategory(newCategory);

            String newAvailability = InputHelper.getValidatedString("Enter new availability (1 for available, 0 for not available, leave blank to keep current):");
            if (!newAvailability.isEmpty()) itemToEdit.setAvailability(Integer.parseInt(newAvailability));

            menuController.editItem(itemToEdit);
            System.out.println("Menu item updated successfully.");
        } else {
            System.out.println("Menu item not found.");
        }
    }

    public void start() {
        while (true) {
            showMenu();
        }
    }
}

