package Controller;

import Entity.Order.Order;
import Entity.Order.OrderStatus;

import java.util.concurrent.*;

public class OrderExpiryController {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final long delayBeforeExpiry;  // Delay in minutes

    public OrderExpiryController(long delayBeforeExpiry) {
        this.delayBeforeExpiry = delayBeforeExpiry;
    }

    public void scheduleOrderExpiry(Order order) {
        Runnable expireOrder = () -> {
            if (order.getOrderStatus() == OrderStatus.READY) {
                order.setOrderStatus(OrderStatus.EXPIRED);
                System.out.println("Order " + order.getOrderID() + " has expired and is now marked as EXPIRED.");
            }
        };

        scheduler.schedule(expireOrder, delayBeforeExpiry, TimeUnit.MINUTES);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
