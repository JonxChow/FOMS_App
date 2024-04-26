package Entity.Payment;

/**
 * Represents the different payment methods available for making a payment.
 */
public enum PaymentMethod {
    /**
     * Payment made using a credit card.
     */
    CREDIT_CARD,

    /**
     * Payment made using a debit card.
     */
    DEBIT_CARD,

    /**
     * Payment made using a PayPal account.
     */
    PAYPAL,

    /**
     * Payment made using Apple Pay.
     */
    APPLE_PAY,

    /**
     * Payment made using Google Pay.
     */
    GOOGLE_PAY,

    /**
     * Payment made using cash.
     */
    CASH
}