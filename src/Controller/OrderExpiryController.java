package Controller;

import Entity.Order.Order;
import Entity.Order.OrderStatus;

import java.util.concurrent.*;

/**
 * Controls the expiration of orders after a set period of time.
 * This controller schedules tasks that will automatically update the status of orders to EXPIRED
 * after they have been READY for a specified duration.
 */
public class OrderExpiryController {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final long delayBeforeExpiry;  // Delay in minutes

    /**
     * Constructs an OrderExpiryController with a specified delay before orders should expire.
     *
     * @param delayBeforeExpiry The delay in minutes after which an order should automatically expire if not completed.
     */
    public OrderExpiryController(long delayBeforeExpiry) {
        this.delayBeforeExpiry = delayBeforeExpiry;
    }

    /**
     * Schedules an order to be automatically marked as EXPIRED after the specified delay.
     * This method uses a ScheduledExecutorService to delay the expiration task.
     * If the order status is READY when the task executes, it will be set to EXPIRED.
     *
     * @param order The order to schedule for expiration.
     */
    public void scheduleOrderExpiry(Order order) {
        Runnable expireOrder = () -> {
            if (order.getOrderStatus() == OrderStatus.READY) {
                order.setOrderStatus(OrderStatus.EXPIRED);
                System.out.println("Order " + order.getOrderID() + " has expired and is now marked as EXPIRED.");
            }
        };

        scheduler.schedule(expireOrder, delayBeforeExpiry, TimeUnit.MINUTES);
    }

    /**
     * Shuts down the ScheduledExecutorService, stopping all pending expiration tasks.
     * It's important to call this method to properly release resources when the application is shutting down
     * or when the controller is no longer needed.
     */
    public void shutdown() {
        scheduler.shutdown();
    }
}
