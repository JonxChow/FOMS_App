package Controller;

import Entity.Branch.Branch;
import Entity.Menu.MenuItem;
import Entity.Order.DiningOption;
import Entity.Order.Order;
import Entity.Order.OrderStatus;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class OrderController {

    public OrderController() {
        // Now an empty constructor, no specific branch associated
    }

    // Create a new order in the specified branch and return the randomly generated order ID
    public int createOrder(Branch branch, DiningOption diningOption) {
        int orderId = ThreadLocalRandom.current().nextInt(10000, 99999 + 1); // Generate a random order ID
        Order newOrder = new Order(orderId);
        newOrder.setDiningOption(diningOption); // Set order type based on user input
        branch.getOrderList().add(newOrder); // Add the order directly to the branch's order list
        return orderId;
    }

    // Add an item to an order in the specified branch
    public void addItemToOrder(Branch branch, int orderId, MenuItem item, int quantity) {
        Order order = findOrderById(branch, orderId);
        if (order != null && item.getAvailability() == 1) {
            order.getOrder().merge(item, quantity, Integer::sum);
            updateTotalAmount(order);
        }
    }

    // Remove an item from an order in the specified branch
    public void removeItemFromOrder(Branch branch, int orderId, MenuItem item) {
        Order order = findOrderById(branch, orderId);
        if (order != null) {
            order.getOrder().remove(item);  // Remove the item entirely, regardless of quantity
            updateTotalAmount(order);
        }
    }

    // Update total amount of the order
    private void updateTotalAmount(Order order) {
        double total = 0.0;
        for (Map.Entry<MenuItem, Integer> entry : order.getOrder().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        order.setTotalAmount(total);
    }

    // Process an order in the specified branch (e.g., set status to completed)
    public void processOrder(Branch branch, int orderId, OrderStatus newStatus) {
        Order order = findOrderById(branch, orderId);
        if (order != null) {
            order.setOrderStatus(newStatus);
        }
    }

    // Collect food to change the order status from “Ready to pickup” to “Completed”
    public void collectFood(Branch branch, int orderId) {
        Order order = findOrderById(branch, orderId);
        if (order != null && order.getOrderStatus() == OrderStatus.READY) {
            order.setOrderStatus(OrderStatus.COMPLETED);
            System.out.println("Order " + orderId + " has been collected and marked as completed.");
        } else {
            System.out.println("Order not ready to be picked up or does not exist.");
        }
    }

    // Print a receipt for an order
    public void printReceipt(Branch branch, int orderId) {
        Order order = findOrderById(branch, orderId);
        if (order != null) {
            System.out.println("Receipt for Order ID: " + orderId);
            System.out.println("Order Type: " + (order.getDiningOption() == DiningOption.TAKEAWAY ? "Takeaway" : "Dine-in"));
            System.out.println("Total Amount: $" + order.getTotalAmount());
            System.out.println("Items Ordered:");
            order.getOrder().forEach((item, qty) -> System.out.println(item.getName() + " x " + qty));
        } else {
            System.out.println("Order does not exist.");
        }
    }

    // Helper method to find an order by its ID within the given branch
    private Order findOrderById(Branch branch, int orderId) {
        for (Order order : branch.getOrderList()) {
            if (order.getOrderID() == orderId) {
                return order;
            }
        }
        return null;
    }

    // Method to list all orders from a specified branch
    public List<Order> listAllOrders(Branch branch) {
        return branch.getOrderList(); // Return a reference to the list of orders
    }

    // Method to view details of a particular order
    public Order viewOrderDetails(Branch branch, int orderId) {
        return findOrderById(branch, orderId); // Utilize the existing method to find an order by ID
    }

    public void checkoutOrder(Branch branch, int orderId) {
        Order order = findOrderById(branch, orderId);
        if (order != null) {
            System.out.println("Checkout Order ID: " + orderId);
            System.out.println("Items in Your Cart:");
            order.getOrder().forEach((item, qty) -> System.out.println(item.getName() + " x " + qty + " = $" + (item.getPrice() * qty)));
            System.out.println("Total Amount: $" + order.getTotalAmount());
        } else {
            System.out.println("Order not found.");
        }
    }

}
