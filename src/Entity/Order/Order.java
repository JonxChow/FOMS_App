package Entity.Order;

import Entity.Menu.MenuItem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an order that can be serialized.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int orderId;
    private Map<MenuItem, Integer> items;
    private double totalAmount;
    protected OrderStatus orderstatus;
    protected DiningOption diningOption;

    private String customisation;

    /**
     * Constructs a new Order object with the specified order ID.
     *
     * @param orderId the unique identifier for the order
     */
    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new HashMap<>();
        this.totalAmount = 0.0;
        this.orderstatus = OrderStatus.PROCESSING; // Default status
        this.customisation = "nil";
    }

    /**
     * Returns the order ID.
     *
     * @return the order ID
     */
    public int getOrderID() {
        return orderId;
    }

    /**
     * Returns the mapping of menu items and their quantities in the order.
     *
     * @return a map containing menu items and their corresponding quantities
     */
    public Map<MenuItem, Integer> getOrder() {
        return items;
    }

    /**
     * Returns the total amount of the order.
     *
     * @return the total amount of the order
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Returns the current status of the order.
     *
     * @return the current status of the order
     */
    public OrderStatus getOrderstatus() {
        return orderstatus;
    }

    /**
     * Sets the total amount of the order.
     *
     * @param totalAmount the new total amount of the order
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Sets the status of the order.
     *
     * @param orderstatus the new status of the order
     */
    public void setOrderStatus(OrderStatus orderstatus) {
        this.orderstatus = orderstatus;
    }

    /**
     * Returns the current status of the order.
     *
     * @return the current status of the order
     */
    public OrderStatus getOrderStatus() {
        return this.orderstatus;
    }

    /**
     * Returns the dining option for the order.
     *
     * @return the dining option for the order
     */
    public DiningOption getDiningOption() {
        return this.diningOption;
    }

    /**
     * Sets the dining option for the order.
     *
     * @param diningOption the new dining option for the order
     */
    public void setDiningOption(DiningOption diningOption) {
        this.diningOption = diningOption;
    }

    /**
     * Sets the customisation for the order.
     *
     * @param customisation the customisation for the order
     */
    public void setCustomisation(String customisation) {
        this.customisation = customisation;
    }

    /**
     * Returns the customisation for the order.
     *
     * @return the customisation for the order
     */
    public String getCustomisation() {
        return customisation;
    }
}