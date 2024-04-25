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
 * Provides an interface for customer interactions, allowing them to create orders, modify them,
 * check status, collect orders, and handle payment processes.
 */
public class CustomerUI implements IDisplayMenu {
    private OrderController orderController;
    private PaymentController paymentMethodController;
    private Branch branch;
    private IAllBranches allBranches;
    private IPaymentMethodUI paymentMethodUI;
    /**
     * Constructs a CustomerUI with necessary controllers and utilities.
     *
     * @param allBranches Interface to access all branches.
     * @param orderController Controller for managing orders.
     * @param paymentMethodController Controller for handling payments.
     * @param paymentMethodUI Interface to display payment method options.
     */
    public CustomerUI(IAllBranches allBranches, OrderController orderController, PaymentController paymentMethodController, IPaymentMethodUI paymentMethodUI) {
        this.orderController = orderController;
        this.paymentMethodController = paymentMethodController;
        this.allBranches = allBranches;
        this.paymentMethodUI = paymentMethodUI;
    }
    /**
     * Displays the main menu for customer actions and handles the flow of customer interactions.
     */
    @Override
    public void displayMenu() {

        //get branch
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

            int choice = InputHelper.getValidatedInt("Select an option",1,5);

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
                    return;
            }
        }
    }

    private void modifyOrder() {
        System.out.println("Enter Order ID to modify, or 0 to create a new order:");
        int orderId = InputHelper.getValidatedInt("ID: ", 0, 99999);

        if (orderId == 0) {
            System.out.println("Choose Dining Option: 1 for Takeaway, 2 for Dine-in");
            int option = InputHelper.getValidatedInt("Option: ", 0, 1);
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
                case 5:
                    modifying = false;
                    break;
            }
        }
    }

    private void addItemToOrder(int orderId) {
        String name = InputHelper.getValidatedString("Enter Item Name:");
        System.out.println("Enter Quantity:");
        int quantity = InputHelper.getValidatedInt("Quantity: ", 0, 500);

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

    private void checkOrderStatus() {
        int orderId = InputHelper.getValidatedInt("Enter Order ID to Check Status: ", 0, 99999);
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null) {
            System.out.println("Order ID: " + orderId + " Status: " + order.getOrderStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    private void collectFood() {
        int orderId = InputHelper.getValidatedInt("Enter Order ID to Collect Food: ", 0, 99999);
        orderController.collectFood(branch, orderId);
    }

    private void handleCheckoutAndPayment() {
        int orderId = InputHelper.getValidatedInt("Enter Order ID to Checkout :", 0, 99999);
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null) {
            System.out.println("Proceeding to Checkout...");
            orderController.checkoutOrder(branch, orderId); // Assuming this method displays the order summary
            System.out.println("Do you wish to proceed with payment?");
            paymentMethodUI.showCurrentPaymentMethods(branch);
            String input = InputHelper.getValidatedString("Yes or No: ");
            if ("yes".equalsIgnoreCase(input)) {
                paymentMethodController.makePayment(branch, orderId, order.getTotalAmount());
                orderController.printReceipt(branch, orderId); // Print the receipt after successful payment
                System.out.println("Payment successful. Receipt has been printed.");
            } else {
                System.out.println("Payment cancelled.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }

    /**
     * Sets the current active branch for the customer UI.
     * @param branch The branch to be set as the active branch.
     */
    public void setBranch(Branch branch) {
        if(branch != null) {this.branch = branch;}
    }

    /**
     * Displays the menu items available at the branch.
     * @param branch The branch whose menu items are displayed.
     */
    private void showMenu(Branch branch) {
        for(MenuItem menuItem : branch.getMenu()) {
            System.out.println(menuItem);
            System.out.println("\n");
        }
    }

    private void addCustomisation(int orderID) {
        String customisation = InputHelper.getValidatedString("Enter your preferences here: ");
        orderController.addCustomisation(branch, orderID, customisation);
    }
}
