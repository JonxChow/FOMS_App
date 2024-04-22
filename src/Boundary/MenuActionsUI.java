package Boundary;

import Entity.Branch.Branch;
import Entity.Menu.MenuItem;
import Helper.InputHelper;
import Interface.Display.IDisplayMenu;

public class MenuActionsUI implements IDisplayMenu {

    private Branch userBranch;

    public MenuActionsUI() {
    }

    @Override
    public void displayMenu() {
        int choice;

        do{
            System.out.println("Manage the Menu");
            System.out.println("1: Add new menu item");
            System.out.println("2: Remove a menu item");
            System.out.println("3: Edit a menu item");
            System.out.println("4: Exit");

            choice = InputHelper.getValidatedInt("Select an option: ", 1, 4);

            switch (choice) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    System.out.println("Exiting");
                    break;
            }

        } while(choice < 4);

    }

    private void addMenuItem() {
        int choice;

        while(true) {
            System.out.println("1: Create an item");
            System.out.println("2: Exit");

            choice = InputHelper.getValidatedInt("Select an Option:", 1, 2);

            if (choice == 1) {
                String name = InputHelper.getValidatedString("Enter name of item: ");
                String description = InputHelper.getValidatedString("Enter description of item: ");
                String category = InputHelper.getValidatedString("Enter Category of item: ");
                double price = InputHelper.getValidatedDouble("Enter price of item:", 0.00, 10000.00);
                int availability = InputHelper.getValidatedInt("Indicate if item is available using 1 or 0 for unavailable:", 0, 1);

                MenuItem menuItem = new MenuItem(name, price, description, category, availability);
                //add in menuController method

                //indicate if successfully added item or not

            } else {
                return;
            }
        }
    }

    private void removeMenuItem() {
        //need to take in branch and get its menu list
        //search list using item name
        //call menuController remove item method
    }

    private void editMenuItem() {
        //use branch to get list
        //search list using name
        //call menuController editItem method
    }
}

