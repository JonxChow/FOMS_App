package Controller;

import Entity.Branch.Branch;
import Entity.Payment.PaymentMethod;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PaymentController {
    // Mapping from branch to a set of accepted payment methods
    private Map<Branch, Set<PaymentMethod>> branchPaymentMethods;

    public PaymentController() {
        branchPaymentMethods = new HashMap<>();
    }

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


    public Set<PaymentMethod> getAcceptedPaymentMethods(Branch branch) {
        return branchPaymentMethods.getOrDefault(branch, EnumSet.noneOf(PaymentMethod.class));
    }

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
