package Controller;

import Entity.Branch.Branch;
import Entity.Payment.PaymentMethod;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PaymentController {
    private Map<Branch, Set<PaymentMethod>> branchPaymentMethods;

    public PaymentController() {
        branchPaymentMethods = new HashMap<>();
    }

    public boolean addPaymentMethod(Branch branch, PaymentMethod method) {
        return branchPaymentMethods.computeIfAbsent(branch, k -> EnumSet.noneOf(PaymentMethod.class)).add(method);
    }

    public boolean removePaymentMethod(Branch branch, PaymentMethod method) {
        Set<PaymentMethod> methods = branchPaymentMethods.get(branch);
        if (methods != null && methods.remove(method)) {
            return true;
        }
        return false;
    }

    public Set<PaymentMethod> getAcceptedPaymentMethods(Branch branch) {
        return branchPaymentMethods.getOrDefault(branch, EnumSet.noneOf(PaymentMethod.class));
    }

    public boolean validateCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

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
