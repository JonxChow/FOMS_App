package Entity.Order;

import Entity.Menu.MenuItem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int orderId;
    private Map<MenuItem, Integer> items;
    private double totalAmount;
    protected OrderStatus orderstatus;
    protected DiningOption diningOption;

    private String customisation;

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new HashMap<>();
        this.totalAmount = 0.0;
        this.orderstatus = OrderStatus.PROCESSING; // Default status
        this.customisation = "nil";
    }

    // Getters and Setters
    public int getOrderID(){return orderId;}
    public Map<MenuItem, Integer> getOrder(){return items;}
    public double getTotalAmount(){return totalAmount;}
    public OrderStatus getOrderstatus(){return orderstatus;}

    public void setTotalAmount(double totalAmount){this.totalAmount = totalAmount;}

    public void setOrderStatus(OrderStatus orderstatus){this.orderstatus = orderstatus;}

    public OrderStatus getOrderStatus(){return this.orderstatus;}
    public DiningOption getDiningOption(){return this.diningOption;}
    public void setDiningOption(DiningOption diningOption){this.diningOption = diningOption;}

    public void setCustomisation(String customisation) {
        this.customisation = customisation;
    }
    public String getCustomisation() {
        return customisation;
    }
}
