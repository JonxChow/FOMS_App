package Entity.Order;

import Entity.Menu.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private final String orderId;
    private final Map<MenuItem, Integer> items;
    private double totalAmount;
    protected OrderStatus orderstatus;

    public Order(String orderId) {
        this.orderId = orderId;
        this.items = new HashMap<>();
        this.orderstatus = OrderStatus.PROCESSING; // Default status
    }

    // Method to add items to the order
//    public void addItem(MenuItem item, int quantity) {
//        items.put(item, quantity);
//        calculateTotal();
//    }
//
//    // Calculate total amount
//    private void calculateTotal() {
//        totalAmount = items.entrySet().stream()
//                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
//                .sum();
//    }

    // Getters and Setters
}
