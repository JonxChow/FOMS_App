package Controller;

import Entity.Branch.Branch;
import Entity.Payment.PaymentMethod;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Manages payment methods for branches and processes payments.
 * This controller allows adding and removing payment methods for each branch and handles payment transactions.
 */
public class PaymentController {
    // Mapping from branch to a set of accepted payment methods
    private Map<Branch, Set<PaymentMethod>> branchPaymentMethods;

    /**
     * Constructs a PaymentController with an empty map to keep track of payment methods for each branch.
     */
    public PaymentController() {
        branchPaymentMethods = new HashMap<>();
    }

    /**
     * Adds a payment method to a specific branch.
     *
     * @param branch The branch to which the payment method will be added.
     * @param method The payment method to add.
     * @return true if the payment method was successfully added, false otherwise.
     */
    public boolean addPaymentMethod(Branch branch, PaymentMethod method) {
        if (branch != null) {
            Set<PaymentMethod> methods = branch.getPaymentMethods(); // Use the getter to access the set.

            if (methods == null) {
                methods = EnumSet.noneOf(PaymentMethod.class); // Initialize if null.
                branch.setPaymentMethods(methods); // Use the setter to update the branch.
            }

            return methods.add(method); // Add the method to the set.
        }
        return false;
    }

    /**
     * Removes a payment method from a specific branch.
     *
     * @param branch The branch from which the payment method will be removed.
     * @param method The payment method to remove.
     * @return true if the payment method was successfully removed, false otherwise.
     */
    public boolean removePaymentMethod(Branch branch, PaymentMethod method) {
        if (branch != null) {
            Set<PaymentMethod> methods = branch.getPaymentMethods(); // Use the getter to access the set.

            if (methods != null && methods.contains(method)) {
                boolean wasRemoved = methods.remove(method); // Attempt to remove the method.
                branch.setPaymentMethods(methods); // Update the set in the branch.
                return wasRemoved; // Return true if the method was successfully removed.
            }
        }
        return false; // Return false if the branch is null, the set is null, or the method is not found.
    }

    /**
     * Retrieves the set of accepted payment methods for a specified branch.
     *
     * @param branch The branch for which to retrieve the accepted payment methods.
     * @return A set of payment methods accepted by the branch.
     */
    public Set<PaymentMethod> getAcceptedPaymentMethods(Branch branch) {
        return branchPaymentMethods.getOrDefault(branch, EnumSet.noneOf(PaymentMethod.class));
    }

    /**
     * Processes a payment for an order within a specific branch.
     *
     * @param branch The branch where the order is placed.
     * @param orderId The ID of the order for which payment is being processed.
     * @param amount The amount of the payment.
     */
    public void makePayment(Branch branch, int orderId, double amount) {
        if (branch == null) {
            System.out.println("Branch not found. Cannot process payment.");
            return;
        }
        // Simulate payment processing
        System.out.println("Processing payment for Order ID: " + orderId + " at " + branch.getBranchName());
        System.out.println("Amount Paid: $" + amount);
        System.out.println("Payment Successful. Thank you!");
    }
}
