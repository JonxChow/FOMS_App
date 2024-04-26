package Controller;

import Entity.Branch.Branch;
import Entity.Payment.PaymentMethod;

import java.util.*;
/**
 * The PaymentController class is responsible for managing payment methods
 * associated with different branches of a restaurant or retail chain.
 * It allows adding and removing payment methods for each branch,
 * retrieving the accepted payment methods for a given branch,
 * and processing payments for orders at a specific branch.
 */
public class PaymentController {
    private Map<Branch, Set<PaymentMethod>> branchPaymentMethods;
    /**
     * Constructs a new instance of the PaymentController.
     */
    public PaymentController() {
        branchPaymentMethods = new HashMap<>();
    }
    /**
     * Adds a payment method to the list of accepted payment methods for a given branch.
     *
     * @param branch the branch to which the payment method should be added
     * @param method the payment method to be added
     * @return true if the payment method was successfully added, false otherwise
     */
    public boolean addPaymentMethod(Branch branch, PaymentMethod method) {
        Set<PaymentMethod> paymentMethods = branch.getPaymentMethods();
        Set<PaymentMethod> updatedPaymentMethods = new HashSet<>(paymentMethods);
        boolean added = updatedPaymentMethods.add(method);
        if (added) {
            branch.setPaymentMethods(updatedPaymentMethods);
            branchPaymentMethods.put(branch, updatedPaymentMethods);
        }
        return added;
    }
    /**
     * Removes a payment method from the list of accepted payment methods for a given branch.
     *
     * @param branch the branch from which the payment method should be removed
     * @param method the payment method to be removed
     * @return true if the payment method was successfully removed, false otherwise
     */
    public boolean removePaymentMethod(Branch branch, PaymentMethod method) {
        Set<PaymentMethod> paymentMethods = branch.getPaymentMethods();
        Set<PaymentMethod> updatedPaymentMethods = new HashSet<>(paymentMethods);
        boolean removed = updatedPaymentMethods.remove(method);
        if (removed) {
            branch.setPaymentMethods(updatedPaymentMethods);
            branchPaymentMethods.put(branch, updatedPaymentMethods);
        }
        return removed;
    }
    /**
     * Retrieves the set of accepted payment methods for a given branch.
     *
     * @param branch the branch for which to retrieve the accepted payment methods
     * @return a set of accepted payment methods for the given branch
     */
    public Set<PaymentMethod> getAcceptedPaymentMethods(Branch branch) {
        return branchPaymentMethods.getOrDefault(branch, EnumSet.noneOf(PaymentMethod.class));
    }
    /**
     * Validates a card number based on a specific format (16 digits).
     *
     * @param cardNumber the card number to be validated
     * @return true if the card number is valid, false otherwise
     */
    public boolean validateCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }
    /**
     * Processes a payment for an order at a given branch.
     *
     * @param branch    the branch where the order is placed
     * @param orderId   the ID of the order
     * @param amount    the amount to be paid
     * @param cardNumber the card number to be used for payment (if applicable)
     * @param method    the payment method to be used
     */
    public void makePayment(Branch branch, int orderId, double amount, String cardNumber, PaymentMethod method) {
        if (branch == null) {
            System.out.println("Branch not found. Cannot process payment.");
            return;
        }
        if (method == PaymentMethod.DEBIT_CARD || method == PaymentMethod.CREDIT_CARD) {
            if (!validateCardNumber(cardNumber)) {
                System.out.println("Invalid card number. Payment could not be processed.");
                return;
            }
        }
        System.out.println("Processing payment for Order ID: " + orderId + " at " + branch.getBranchName() + " with " + method);
        System.out.println("Amount Paid: $" + amount);
        System.out.println("Payment Successful. Thank you!");
    }
}
