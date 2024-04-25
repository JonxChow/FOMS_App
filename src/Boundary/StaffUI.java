package Boundary;

import Controller.OrderController;
import Entity.Branch.Branch;
import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Interface.Boundaries.IStaffUI;
import Interface.Display.IDisplayMenu;

import java.util.List;
import java.util.Scanner;

/**
 * Provides a user interface for staff members to manage orders within a branch.
 */
public class StaffUI implements IStaffUI {
    private OrderController orderController;
    private Branch branch;
    private Scanner scanner;

    /**
     * Constructs a StaffUI object with the provided OrderController.
     * @param orderController The OrderController used for managing orders.
     */
    public StaffUI(OrderController orderController) {
        this.orderController = orderController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the menu for staff to manage orders and handles user interactions.
     */
    @Override
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Staff Menu ---");
            System.out.println("1. Display New Orders");
            System.out.println("2. View Order Details");
            System.out.println("3. Process Order");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline left-over

            switch (choice) {
                case 1:
                    displayNewOrders();
                    break;
                case 2:
                    viewOrderDetails();
                    break;
                case 3:
                    processOrder();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * Displays the new orders for the current branch.
     */
    private void displayNewOrders() {
        List<Order> orders = orderController.listAllOrders(branch);
        System.out.println("\n--- New Orders ---");
        orders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.PROCESSING)
                .forEach(order -> System.out.println("Order ID: " + order.getOrderID() + " - Status: " + order.getOrderStatus()));
    }

    /**
     * Displays the details of a specific order.
     */
    private void viewOrderDetails() {
        System.out.print("Enter the Order ID to view details: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null) {
            System.out.println("\nOrder ID: " + order.getOrderID() +
                    ", Total Amount: $" + order.getTotalAmount() +
                    ", Status: " + order.getOrderStatus());
            order.getOrder().forEach((item, qty) -> System.out.println(item.getName() + " x " + qty));
        } else {
            System.out.println("Order not found.");
        }
    }

    /**
     * Processes a specific order, setting it to 'READY' status.
     */
    private void processOrder() {
        System.out.print("Enter the Order ID to process: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null && order.getOrderStatus() == OrderStatus.PROCESSING) {
            orderController.processOrder(branch, orderId, OrderStatus.READY);
            orderController.setOrderReady(order);
            System.out.println("Order " + orderId + " is now Ready to Pickup.");
        } else {
            System.out.println("Order not found or is not in PROCESSING status.");
        }
    }

    /**
     * Sets the branch for the staff UI.
     * @param branch The branch to be set.
     */
    @Override
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
