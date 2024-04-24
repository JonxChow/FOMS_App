package Controller;

import Entity.Branch.Branch;
import Entity.Payment.PaymentType;
import Interface.Admin.AddPaymentMethod;
import Interface.Admin.RemovePaymentMethod;

/*Class responsibility is to handle adding and removing of a payment method of a branch*/

public class PaymentController implements AddPaymentMethod, RemovePaymentMethod {

    public PaymentController(){};

    public void addPaymentMethod(Branch b, String type) {
        b.getPaymentMethods().addPaymentType(new PaymentType(type));
    }

    public void removePaymentMethod(Branch b, String type) {
        b.getPaymentMethods().removePaymentType(type);
    }

    public void printPage() {
        System.out.println("*******SELECT*********");
        System.out.println("1: To add payment method");
        System.out.println("2: To remove payment method");
    }

    public void makePayment(int orderId, double amount) {
        // Simulate payment processing
        System.out.println("Processing payment for Order ID: " + orderId);
        System.out.println("Amount Paid: $" + amount);
        System.out.println("Payment Successful. Thank you!");
    }
}
