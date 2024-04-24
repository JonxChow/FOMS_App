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
        Set<PaymentMethod> methods = branchPaymentMethods.computeIfAbsent(branch, k -> EnumSet.noneOf(PaymentMethod.class));
        return methods.add(method);
    }

    public boolean removePaymentMethod(Branch branch, PaymentMethod method) {
        if (branchPaymentMethods.containsKey(branch)) {
            Set<PaymentMethod> methods = branchPaymentMethods.get(branch);
            return methods.remove(method);
        }
        return false;
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
