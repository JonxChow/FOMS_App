package Controller;

import Entity.Branch.Branch;
import Entity.Payment.PaymentType;
import Interface.Admin.AddPaymentMethod;
import Interface.Admin.RemovePaymentMethod;

/*Class responsibility is to handle adding and removing of a payment method of a branch*/

public class PaymentController implements AddPaymentMethod, RemovePaymentMethod {

    public void addPaymentMethod(Branch b, String type) {
        b.getPaymentMethods().addPaymentType(new PaymentType(type));
    }

    public void removePaymentMethod(Branch b, String type) {
        b.getPaymentMethods().removePaymentType(type);
    }
}
