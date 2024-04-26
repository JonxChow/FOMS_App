package Boundary;

import Controller.OrderController;
import Controller.PaymentController;
import Entity.Branch.Branch;
import Entity.Menu.MenuItem;
import Entity.Order.DiningOption;
import Entity.Order.Order;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IPaymentMethodUI;
import Interface.Display.IDisplayMenu;

import java.util.Scanner;

/**
 * Handles the customer interactions with the restaurant's order and payment system, providing
 * a menu-driven interface to create and manage orders, and handle payments.
 */
public class CustomerUI implements IDisplayMenu {
    private OrderController orderController;
    private PaymentController paymentMethodController;
    private Branch branch;
    private IAllBranches allBranches;
    private IPaymentMethodUI paymentMethodUI;

    /**
     * Constructor initializing the UI with required controllers and interfaces.
     *
     * @param allBranches Interface to access all branches.
     * @param orderController Controller for managing orders.
     * @param paymentMethodController Controller for handling payments.
     * @param paymentMethodUI Interface to display payment method options.
     */
    public CustomerUI(IAllBranches allBranches, OrderController orderController,
                      PaymentController paymentMethodController, IPaymentMethodUI paymentMethodUI) {
        this.orderController = orderController;
        this.paymentMethodController = paymentMethodController;
        this.allBranches = allBranches;
        this.paymentMethodUI = paymentMethodUI;
    }

    /**
     * Displays the main menu for customer actions and manages the flow of interactions.
     */
    @Override
    public void displayMenu() {
        String branchName = InputHelper.getValidatedString("Enter Branch to visit: ");
        Branch branch = allBranches.getBranchByName(branchName);
        setBranch(branch);

        System.out.println("\n--- Customer Menu ---");
        System.out.println("\n--- Current Menu Available ---");
        showMenu(branch);

        while (true) {
            System.out.println("1. New Order / Modify Existing Order");
            System.out.println("2. Check Order Status");
            System.out.println("3. Collect Food");
            System.out.println("4. Checkout and Make Payment");
            System.out.println("5. Exit");

            int choice = InputHelper.getValidatedInt("Select an option", 1, 5);

            switch (choice) {
                case 1:
                    modifyOrder();
                    break;
                case 2:
                    checkOrderStatus();
                    break;
                case 3:
                    collectFood();
                    break;
                case 4:
                    handleCheckoutAndPayment();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return; // Exits the loop and thus the menu
            }
        }
    }

    /**
     * Manages modifications to an existing order or creates a new order.
     */
    private void modifyOrder() {
        System.out.println("Enter Order ID to modify, or 0 to create a new order:");
        int orderId = InputHelper.getValidatedInt("ID: ", 0, 99999);

        // Logic to handle new order creation
        if (orderId == 0) {
            System.out.println("Choose Dining Option: 1 for Takeaway, 2 for Dine-in");
            int option = InputHelper.getValidatedInt("Option: ", 1, 2);
            DiningOption diningOption = (option == 1) ? DiningOption.TAKEAWAY : DiningOption.DINE_IN;
            orderId = orderController.createOrder(branch, diningOption);
            System.out.println("New Order Created. Order ID: " + orderId);
        }

        boolean modifying = true;
        while (modifying) {
            System.out.println("1. Add Item to Order");
            System.out.println("2. Remove Item from Order");
            System.out.println("3: Add customisation comments");
            System.out.println("4: View Cart");
            System.out.println("5. Finished Modifying");
            int choice = InputHelper.getValidatedInt("Option: ", 1, 5);

            switch (choice) {
                case 1:
                    addItemToOrder(orderId);
                    break;
                case 2:
                    removeItemFromOrder(orderId);
                    break;
                case 3:
                    addCustomisation(orderId);
                    break;
                case 4:
                    orderController.printCart(branch, orderId);
                    break;
                case 5:
                    modifying = false; // Exits modification loop
                    break;
            }
        }
    }

    /**
     * Adds an item to an order.
     * @param orderId the ID of the order to modify.
     */
    private void addItemToOrder(int orderId) {
        String name = InputHelper.getValidatedString("Enter Item Name:");
        int quantity = InputHelper.getValidatedInt("Quantity: ", 1, 500); // Validate that at least 1 item is added

        MenuItem item = branch.getMenu().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (item != null) {
            orderController.addItemToOrder(branch, orderId, item, quantity);
            System.out.println("Item added successfully.");
        } else {
            System.out.println("Item not found in menu.");
        }
    }

    /**
     * Removes an item from an order.
     * @param orderId the ID of the order to modify.
     */
    private void removeItemFromOrder(int orderId) {
        String name = InputHelper.getValidatedString("Enter Item Name to Remove:");
        MenuItem item = branch.getMenu().stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (item != null) {
            orderController.removeItemFromOrder(branch, orderId, item);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found in order.");
        }
    }

    /**
     * Checks the status of an existing order.
     */
    private void checkOrderStatus() {
        int orderId = InputHelper.getValidatedInt("Enter Order ID to Check Status: ", 0, 99999);
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null) {
            System.out.println("Order ID: " + orderId + " Status: " + order.getOrderStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    /**
     * Facilitates the collection of prepared food for an order.
     */
    private void collectFood() {
        int orderId = InputHelper.getValidatedInt("Enter Order ID to Collect Food: ", 0, 99999);
        orderController.collectFood(branch, orderId);
    }

    /**
     * Handles the checkout and payment process for an order.
     */
    private void handleCheckoutAndPayment() {
        int orderId = InputHelper.getValidatedInt("Enter Order ID to Checkout: ", 0, 99999);
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null) {
            System.out.println("Proceeding to Checkout...");
            orderController.checkoutOrder(branch, orderId);
            System.out.println("Do you wish to proceed with payment?");
            paymentMethodUI.showCurrentPaymentMethods(branch);
            String input = InputHelper.getValidatedString("Yes or No: ");
            if ("yes".equalsIgnoreCase(input)) {
                if(order.getOrder().isEmpty()){
                    System.out.println("Order is empty. Please add menu items.");
                    return;
                }
                paymentMethodController.makePayment(branch, orderId, order.getTotalAmount());
                orderController.printReceipt(branch, orderId);
                System.out.println("Payment successful. Receipt has been printed.");
            } else {
                System.out.println("Payment cancelled.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }

    /**
     * Sets the branch as the current active branch for the UI.
     * @param branch The branch to set as active.
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    /**
     * Displays the menu items available at the branch.
     * @param branch The branch whose menu is displayed.
     */
    private void showMenu(Branch branch) {
        for (MenuItem menuItem : branch.getMenu()) {
            System.out.println(menuItem);
            System.out.println("\n");
        }
    }

    /**
     * Adds customisation comments to an order.
     * @param orderID The ID of the order to modify.
     */
    private void addCustomisation(int orderID) {
        String customisation = InputHelper.getValidatedString("Enter your preferences here: ");
        orderController.addCustomisation(branch, orderID, customisation);
    }
}
