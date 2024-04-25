package Controller;

import Entity.Branch.Branch;
import Entity.Menu.MenuItem;
import Entity.Order.DiningOption;
import Entity.Order.Order;
import Entity.Order.OrderStatus;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

/**
 * Manages orders within a branch, including creation, modification, and status updates.
 * This controller handles the lifecycle of orders from creation through completion.
 */
public class OrderController {
    private final OrderExpiryController expiryManager = new OrderExpiryController(1);  // Orders expire after 1 minutes

    /**
     * Constructs an OrderController.
     */
    public OrderController() {
        // Empty constructor, potentially for future use
    }

    /**
     * Creates a new order in the specified branch with a specified dining option.
     * @param branch The branch in which the order is to be created.
     * @param diningOption The dining option (e.g., TAKEAWAY or DINE_IN).
     * @return the order ID of the newly created order.
     */
    public int createOrder(Branch branch, DiningOption diningOption) {
        int orderId = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
        Order newOrder = new Order(orderId);
        newOrder.setDiningOption(diningOption);
        branch.getOrderList().add(newOrder);
        return orderId;
    }

    /**
     * Adds an item to an existing order within a branch.
     * @param branch The branch where the order exists.
     * @param orderId The ID of the order to modify.
     * @param item The menu item to add.
     * @param quantity The quantity of the menu item to add.
     */
    public void addItemToOrder(Branch branch, int orderId, MenuItem item, int quantity) {
        Order order = findOrderById(branch, orderId);
        if (order != null && item.getAvailability() == 1) {
            order.getOrder().merge(item, quantity, Integer::sum);
            updateTotalAmount(order);
        }
    }

    /**
     * Removes an item from an existing order within a branch.
     * @param branch The branch where the order exists.
     * @param orderId The ID of the order to modify.
     * @param item The menu item to remove.
     */
    public void removeItemFromOrder(Branch branch, int orderId, MenuItem item) {
        Order order = findOrderById(branch, orderId);
        if (order != null) {
            order.getOrder().remove(item);
            updateTotalAmount(order);
        }
    }

    /**
     * Updates the total amount of the order based on the current items and quantities.
     * @param order The order to update.
     */
    private void updateTotalAmount(Order order) {
        double total = 0.0;
        for (Map.Entry<MenuItem, Integer> entry : order.getOrder().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        order.setTotalAmount(total);
    }

    /**
     * Processes an order by setting its status, e.g., READY or COMPLETED.
     * @param branch The branch where the order exists.
     * @param orderId The ID of the order to process.
     * @param newStatus The new status to set for the order.
     */
    public void processOrder(Branch branch, int orderId, OrderStatus newStatus) {
        Order order = findOrderById(branch, orderId);
        if (order != null) {
            order.setOrderStatus(newStatus);
        }
    }

    /**
     * Marks an order as collected and changes its status to COMPLETED.
     * @param branch The branch where the order exists.
     * @param orderId The ID of the order to collect.
     */
    public void collectFood(Branch branch, int orderId) {
        Order order = findOrderById(branch, orderId);
        if (order != null && order.getOrderStatus() == OrderStatus.READY) {
            order.setOrderStatus(OrderStatus.COMPLETED);
            System.out.println("Order " + orderId + " has been collected and marked as completed.");
        } else {
            System.out.println("Order not ready to be picked up or does not exist.");
        }
    }

    /**
     * Prints a receipt for a specified order.
     * @param branch The branch where the order exists.
     * @param orderId The ID of the order for which to print a receipt.
     */
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

    /**
     * Finds an order by ID within a specific branch.
     * @param branch The branch to search within.
     * @param orderId The ID of the order to find.
     * @return the found Order or null if no order matches the ID.
     */
    private Order findOrderById(Branch branch, int orderId) {
        for (Order order : branch.getOrderList()) {
            if (order.getOrderID() == orderId) {
                return order;
            }
        }
        return null;
    }

    /**
     * Lists all orders from a specified branch.
     * @param branch The branch whose orders are to be listed.
     * @return a list of all orders in the branch.
     */
    public List<Order> listAllOrders(Branch branch) {
        return branch.getOrderList();
    }

    /**
     * Provides details of a particular order.
     * @param branch The branch where the order exists.
     * @param orderId The ID of the order to view.
     * @return the detailed Order or null if it does not exist.
     */
    public Order viewOrderDetails(Branch branch, int orderId) {
        return findOrderById(branch, orderId);
    }

    /**
     * Prepares an order for checkout by providing a summary of items and costs.
     * @param branch The branch where the order exists.
     * @param orderId The ID of the order to checkout.
     */
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

    /**
     * Sets an order status to READY and schedules it for expiry after a predefined time if not collected.
     * @param order The order to be marked as READY.
     */
    public void setOrderReady(Order order) {
        order.setOrderStatus(OrderStatus.READY);
        expiryManager.scheduleOrderExpiry(order);
    }

    /**
     * Prints the current items and their quantities in a cart for a specified order.
     * @param branch The branch where the order exists.
     * @param orderID The ID of the order whose cart is to be printed.
     */
    public void printCart(Branch branch, int orderID) {
        Order order = findOrderById(branch, orderID);
        assert  order != null;
        order.getOrder().forEach((item, qty) -> System.out.println(item.getName() + " x " + qty + " = $" + (item.getPrice() * qty)));
    }

}
