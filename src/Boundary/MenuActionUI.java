package Boundary;

import Controller.MenuController;
import Entity.Branch.Branch;
import Entity.Menu.MenuItem;
import Helper.InputHelper;
import Interface.Boundaries.IMenuActionUI;
import Interface.Display.IDisplayMenu;

import java.util.Scanner;

/**
 * Provides a user interface for performing actions related to menu items such as adding, removing,
 * editing, and displaying items within a specific branch's menu.
 */
public class MenuActionUI implements IMenuActionUI {
    private MenuController menuController;
    private Branch branch;
    private Scanner scanner;

    /**
     * Constructs a MenuActionUI and initializes the underlying MenuController.
     */
    public MenuActionUI() {
        this.menuController = new MenuController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the menu for menu item management and handles user interactions for
     * adding, removing, editing, and listing menu items.
     */
    @Override
    public void displayMenu() {
        int choice = 0;

        do {
            System.out.println("1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Edit Menu Item");
            System.out.println("4. Display Menu");
            System.out.println("5. Exit");
            choice = InputHelper.getValidatedInt("Choose an option: ", 1, 5);

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
                    showMenu();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (choice < 5);
    }

    /**
     * Displays all menu items from the branch's menu.
     */
    public void showMenu() {
        menuController.displayMenu(branch);
    }

    /**
     * Interactively adds a new menu item to the branch's menu.
     */
    public void addMenuItem() {
        String name = InputHelper.getValidatedString("Enter the name of the new menu item:");
        if(menuController.isItemNameExists(branch, name)){
            System.out.println("Menu item already exist in thus branch. Please try adding another item.");
            return;
        }
        double price = Double.parseDouble(InputHelper.getValidatedString("Enter the price of the new menu item:"));
        String description = InputHelper.getValidatedString("Enter the description of the new menu item:");
        String category = InputHelper.getValidatedString("Enter the category of the new menu item:");
        int availability = InputHelper.getValidatedInt("Enter availability (1 for available, 0 for not available):", 0, 1);

        MenuItem newItem = new MenuItem(name, price, description, category, availability);
        menuController.addItem(branch, newItem);
    }

    /**
     * Interactively removes a specified menu item from the branch's menu.
     */
    public void removeMenuItem() {
        String name = InputHelper.getValidatedString("Enter the name of the menu item you want to remove:");
        MenuItem item = branch.getMenu().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (item != null) {
            menuController.removeItem(branch, item);
        } else {
            System.out.println("Menu item not found.");
        }
    }

    /**
     * Interactively edits details of a specified menu item in the branch's menu.
     */
    public void editMenuItem() {
        String name = InputHelper.getValidatedString("Enter the name of the menu item you want to edit:");
        MenuItem itemToEdit = branch.getMenu().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (itemToEdit != null) {
            MenuItem newItem = new MenuItem(name,
                    Double.parseDouble(InputHelper.getValidatedString("Enter new price (retype current to keep " + itemToEdit.getPrice() + "):")),
                    InputHelper.getValidatedString("Enter new description (retype current to keep \"" + itemToEdit.getDescription() + "\"):"),
                    InputHelper.getValidatedString("Enter new category (retype current to keep \"" + itemToEdit.getCategory() + "\"):"),
                    InputHelper.getValidatedInt("Enter new availability (1 for available, 0 for not available, retype current to keep " + itemToEdit.getAvailability() + "):", 0, 1));

            menuController.editItem(branch, newItem);
        } else {
            System.out.println("Menu item not found.");
        }
    }

    /**
     * Sets the current working branch context for the UI.
     * @param branch The branch whose menu will be managed.
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
