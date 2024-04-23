package Controller;

import Entity.Branch.Branch;
import Entity.Menu.MenuItem;
import Entity.Order.Order;
import Entity.Order.OrderStatus;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class OrderController {

    public OrderController() {}

    // Create a new order in the specified branch and return the randomly generated order ID
    public int createOrder(Branch branch) {
        int orderId = ThreadLocalRandom.current().nextInt(10000, 99999 + 1); // Generate a random order ID
        Order newOrder = new Order(orderId);
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
            Integer currentQuantity = order.getOrder().get(item);
            if (currentQuantity != null) {
                if (currentQuantity > 1) {
                    order.getOrder().put(item, currentQuantity - 1);
                } else {
                    order.getOrder().remove(item);
                }
                updateTotalAmount(order);
            }
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

    // Remove an entire order from the specified branch
    public void removeOrder(Branch branch, int orderId) {
        Order order = findOrderById(branch, orderId);
        if (order != null) {
            branch.getOrderList().remove(order);
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
}
