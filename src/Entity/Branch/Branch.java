package Entity.Branch;

import Entity.Menu.Menu;
import Entity.Order.Order;
import Entity.Actor.Staff;
import Entity.Payment.PaymentMethods;
import Entity.Payment.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String branchName;
    private final String location;
    private ArrayList<Staff> staffMembers;
    private int noOfStaff;
    private int noOfManager;
    private ArrayList<Order> orderList;
    private Menu menu; // Assuming each branch can have a unique menu

    private PaymentMethods paymentMethods; //All the available payment types of a branch

    public Branch(String branchName, String location) {
        this.branchName = branchName;
        this.location = location;
        this.staffMembers = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.menu = new Menu();
        this.paymentMethods = new PaymentMethods();
        this.noOfStaff = 0;
        this.noOfManager = 0;
    }

    // Methods to add or remove staff, update menu, etc.


    public String getBranchName() {
        return branchName;
    }

    // Getters and Setters
    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public ArrayList<Staff> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(ArrayList<Staff> staffMembers) {
        this.staffMembers = staffMembers;
    }

//    public void addStaff(Staff staff) {
//        this.staffMembers.add(staff);
//    }
}
