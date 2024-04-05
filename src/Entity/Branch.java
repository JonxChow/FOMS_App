package Entity;

import Entity.Menu.Menu;
import Entity.Order.Order;
import Entity.Person.Staff;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String branchId;
    private final String location;
    private List<Staff> staffMembers;
    private List<Order> orderList;
    private Menu menu; // Assuming each branch can have a unique menu

    public Branch(String branchId, String location) {
        this.branchId = branchId;
        this.location = location;
        this.staffMembers = new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.menu = new Menu();
    }

    // Methods to add or remove staff, update menu, etc.

    // Getters and Setters
}
