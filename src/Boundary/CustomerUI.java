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

public class CustomerUI implements IDisplayMenu {
    private OrderController orderController;
    private PaymentController paymentMethodController;
    private Branch branch;
    private Scanner scanner;

    private IAllBranches allBranches;
    private IPaymentMethodUI paymentMethodUI;

    public CustomerUI(IAllBranches allBranches, OrderController orderController, PaymentController paymentMethodController, IPaymentMethodUI paymentMethodUI) {
        this.orderController = orderController;
        this.paymentMethodController = paymentMethodController;
        this.scanner = new Scanner(System.in);
        this.allBranches = allBranches;
        this.paymentMethodUI = paymentMethodUI;
    }
    @Override
    public void displayMenu() {

        //get branch
        String branchName = InputHelper.getValidatedString("Enter Branch to visit: ");
        Branch branch = allBranches.getBranchByName(branchName);
        setBranch(branch);

        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("\n--- Current Menu Available ---");
            showMenu(branch);
            System.out.println("1. New Order / Modify Existing Order");
            System.out.println("2. Check Order Status");
            System.out.println("3. Collect Food");
            System.out.println("4. Checkout and Make Payment");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline left-over

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
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void modifyOrder() {
        System.out.println("Enter Order ID to modify, or 0 to create a new order:");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        if (orderId == 0) {
            System.out.println("Choose Dining Option: 1 for Takeaway, 2 for Dine-in");
            int option = scanner.nextInt();
            DiningOption diningOption = (option == 1) ? DiningOption.TAKEAWAY : DiningOption.DINE_IN;
            orderId = orderController.createOrder(branch, diningOption);
            System.out.println("New Order Created. Order ID: " + orderId);
        }

        boolean modifying = true;
        while (modifying) {
            System.out.println("1. Add Item to Order");
            System.out.println("2. Remove Item from Order");
            System.out.println("3. Finished Modifying");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItemToOrder(orderId);
                    break;
                case 2:
                    removeItemFromOrder(orderId);
                    break;
                case 3:
                    modifying = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void addItemToOrder(int orderId) {
        System.out.println("Enter Item Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
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
        System.out.println("Enter Item Name to Remove:");
        String name = scanner.nextLine();
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
        System.out.println("Enter Order ID to Check Status:");
        int orderId = scanner.nextInt();
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null) {
            System.out.println("Order ID: " + orderId + " Status: " + order.getOrderStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    private void collectFood() {
        System.out.println("Enter Order ID to Collect Food:");
        int orderId = scanner.nextInt();
        orderController.collectFood(branch, orderId);
    }

    private void handleCheckoutAndPayment() {
        System.out.println("Enter Order ID to Checkout:");
        int orderId = scanner.nextInt();
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null) {
            System.out.println("Proceeding to Checkout...");
            orderController.checkoutOrder(branch, orderId); // Assuming this method displays the order summary
            System.out.println("Do you wish to proceed with payment? (yes/no)");
            paymentMethodUI.showCurrentPaymentMethods(branch);
            String input = scanner.next();
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

    public void setBranch(Branch branch) {
        if(branch != null) {this.branch = branch;}
    }

    private void showMenu(Branch branch) {
        for(MenuItem menuItem : branch.getMenu()) {
            System.out.println(menuItem);
            System.out.println("\n");
        }
    }
}
