package Entity.Payment;

import java.util.ArrayList;
import java.util.List;

/*This class will store all the available payment methods of a branch
* Class responsibility is to handle all the payment methods of a branch*/
public class PaymentMethods {

    private List<PaymentType> paymentMethods;

    public PaymentMethods () {
        this.paymentMethods = new ArrayList<>();
    }

    private int getPaymentTypeIndex(String type) {
        for(int i = 0; i < paymentMethods.size(); i++) {
           if(paymentMethods.get(i).getType().equals(type)){
               return i;
           }
        }

        return -1;
    }

    public void addPaymentType(PaymentType paymentType) {
        paymentMethods.add(paymentType);
    }

    public void removePaymentType(String type) {
        int index = getPaymentTypeIndex(type);

        if(index == -1) {
            System.out.println("Payment Method doesnt exist in this branch");
        } else {
            paymentMethods.remove(index);
        }
    }
}
