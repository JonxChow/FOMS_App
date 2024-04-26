package Entity.Order;

/**
 * Represents the different possible status values for an order.
 */
public enum OrderStatus {
    /**
     * The order is currently being processed.
     */
    PROCESSING,

    /**
     * The order is ready for pickup or delivery.
     */
    READY,

    /**
     * The order has been completed and fulfilled.
     */
    COMPLETED,

    /**
     * The order has expired and is no longer valid.
     */
    EXPIRED
}