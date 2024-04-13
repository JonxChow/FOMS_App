package Controller;

import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.IStaffManager;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffManagementController implements IStaffManager {

    private final Scanner scanner = new Scanner(System.in);


    @Override
    public void addStaff(Branch branch, int noOfStaff) {
        for(int i = 0; i < noOfStaff; i++) {
            System.out.println("Enter staff name: ");
            String name = scanner.nextLine();
            ArrayList<Staff> staffMembers = branch.getStaffMembers();
            staffMembers.add(new Staff(-1, name));
            branch.setStaffMembers(staffMembers);
        }
    }

    @Override
    public void addManager(Branch branch, int noOfManagers) {
        for(int i = 0; i < noOfManagers; i++) {
            System.out.println("Enter Manager name: ");
            String name = scanner.nextLine();
            ArrayList<Staff> staffMembers = branch.getStaffMembers();
            staffMembers.add(new Manager(-1, name));
            branch.setStaffMembers(staffMembers);
        }

    }

    @Override
    public void removeStaff(Branch branch, Staff staff) {

    }

    @Override
    public void removeManger(Branch branch, Manager manager) {

    }
}
