package Entity.Branch;

import Entity.Actor.Manager;
import Entity.Actor.Role;
import Entity.Menu.Menu;
import Entity.Menu.MenuItem;
import Entity.Order.Order;
import Entity.Actor.Staff;
import Entity.Payment.PaymentMethod;
import Entity.Payment.PaymentMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

public class Branch implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String branchName;
    private final String location;
    private ArrayList<Staff> staffMembers;
    private int noOfStaff;
    private int noOfManager;
    private ArrayList<Order> orderList;
    private ArrayList<MenuItem> menu; // Assuming each branch can have a unique menu

    private Set<PaymentMethod> paymentMethod; //All the available payment types of a branch

    public Branch(String branchName, String location) {
        this.branchName = branchName;
        this.location = location;
        this.staffMembers = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.paymentMethod = EnumSet.noneOf(PaymentMethod.class);
        this.noOfStaff = 0;
        this.noOfManager = 0;
    }

    // Methods to add or remove staff, update menu, etc.


    public String getBranchName() {
        return branchName;
    }

    // Getters and Setters
    public Set<PaymentMethod> getPaymentMethods() {
        return paymentMethod;
    }

    public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
        this.paymentMethod = paymentMethods;
    }

    public ArrayList<Staff> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(ArrayList<Staff> staffMembers) {
        this.staffMembers = staffMembers;
        updateNoOfStaff();
    }

    private int getIndexByPredicate(Predicate<Staff> predicate) {
        for (int i = 0; i < staffMembers.size(); i++) {
            if (predicate.test(staffMembers.get(i))) {
                return i;
            }
        }
        return -1;  // Return -1 if no match found
    }

    public Staff getStaffByName(String name) {
        return getStaffByPredicate(staff -> staff.getName().equals(name));
    }

    public Staff getStaffByUsername(String username) {
        return getStaffByPredicate(staff -> staff.getUsername().equals(username));
    }

    private Staff getStaffByPredicate(Predicate<Staff> predicate) {
        int index = getIndexByPredicate(predicate);
        if (index != -1) {
            return staffMembers.get(index);
        }
        return null;
    }

    public Staff getUnassignedStaff(Role role) {
        for (Staff staffMember : staffMembers) {
            if (staffMember.getId() == -1 && staffMember.getStaffRole().equals(role)) {
                return staffMember;
            }
        }

        return null;
    }
    public ArrayList<MenuItem> getMenu(){return menu;}
    public ArrayList<Order> getOrderList(){return orderList;}

    public int getNumberOfStaffByRole(Role role) {
        return (int) staffMembers.stream()
                .filter(staff -> staff.getStaffRole().equals(role))
                .count();
    }

    private void updateNoOfStaff() {
        for(Staff staff: staffMembers) {
            if(staff.getStaffRole() == Role.STAFF) {
                noOfStaff++;
            } else if(staff.getStaffRole() == Role.MANAGER) {
                noOfManager++;
            }
        }
    }

    public int getNoOfManager() {
        updateNoOfStaff();
        return noOfManager;
    }

    public int getNoOfStaff() {
        updateNoOfStaff();
        return noOfStaff;
    }
}
