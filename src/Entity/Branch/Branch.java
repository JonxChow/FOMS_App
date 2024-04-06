package Entity.Branch;

import Entity.Menu.Menu;
import Entity.Order.Order;
import Entity.Actor.Staff;
import Entity.Payment.PaymentMethods;
import Entity.Payment.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String branchId;
    private final String location;
    private List<Staff> staffMembers;
    private List<Order> orderList;
    private Menu menu; // Assuming each branch can have a unique menu

    private PaymentMethods paymentMethods; //All the available payment types of a branch

    public Branch(String branchId, String location) {
        this.branchId = branchId;
        this.location = location;
        this.staffMembers = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.menu = new Menu();
        this.paymentMethods = new PaymentMethods();
    }

    // Methods to add or remove staff, update menu, etc.

    // Getters and Setters
    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }
}
