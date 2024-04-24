package Boundary;

import Controller.OrderController;
import Entity.Branch.Branch;
import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Interface.Display.IDisplayMenu;

import java.util.List;
import java.util.Scanner;

public class StaffUI implements IDisplayMenu {
    private OrderController orderController;
    private Branch branch;
    private Scanner scanner;

    public StaffUI(OrderController orderController) {
        //this.branch = branch;
        this.orderController = orderController;
        this.scanner = new Scanner(System.in);
    }

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

    private void displayNewOrders() {
        List<Order> orders = orderController.listAllOrders(branch);
        System.out.println("\n--- New Orders ---");
        orders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.PROCESSING)
                .forEach(order -> System.out.println("Order ID: " + order.getOrderID() + " - Status: " + order.getOrderStatus()));
    }

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

    private void processOrder() {
        System.out.print("Enter the Order ID to process: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        Order order = orderController.viewOrderDetails(branch, orderId);
        if (order != null && order.getOrderStatus() == OrderStatus.PROCESSING) {
            orderController.processOrder(branch, orderId, OrderStatus.READY);
            System.out.println("Order " + orderId + " is now Ready to Pickup.");
        } else {
            System.out.println("Order not found or is not in PROCESSING status.");
        }
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}

