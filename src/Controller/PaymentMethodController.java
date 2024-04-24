package Controller;

import Entity.Payment.PaymentMethod;

import java.util.EnumSet;
import java.util.Set;

public class PaymentMethodController {
    private Set<PaymentMethod> acceptedPaymentMethods;

    public PaymentMethodController() {
        // Initialize with default accepted methods
        acceptedPaymentMethods = EnumSet.of(PaymentMethod.CREDIT_CARD, PaymentMethod.DEBIT_CARD, PaymentMethod.CASH);
    }

    public void addPaymentMethod(PaymentMethod method) {
        acceptedPaymentMethods.add(method);
    }

    public void removePaymentMethod(PaymentMethod method) {
        acceptedPaymentMethods.remove(method);
    }

    public Set<PaymentMethod> getAcceptedPaymentMethods() {
        return acceptedPaymentMethods;
    }
}

