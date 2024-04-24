package Controller;

import Entity.Actor.Gender;
import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Controllers.IStaffManager;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffManagementController implements IStaffManager {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean addStaff(Branch branch, Staff newStaff) {
        if (branch != null) {
            ArrayList<Staff> staffMembers = branch.getStaffMembers();
            staffMembers.add(newStaff);
            branch.setStaffMembers(staffMembers);
            return true;
        }
        return false;
    }
    @Override
    public boolean addManager(Branch branch, Manager newManager) {
        if (branch != null) {
            ArrayList<Staff> staffMembers = branch.getStaffMembers();
            staffMembers.add(newManager);
            branch.setStaffMembers(staffMembers);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeStaff(Branch branch, String staffName) {
        if (branch != null) {
            ArrayList<Staff> staffMembers = branch.getStaffMembers();
            Staff staffToRemove = staffMembers.stream()
                    .filter(s -> s.getName().equals(staffName))
                    .findFirst()
                    .orElse(null);
            if (staffToRemove != null) {
                staffMembers.remove(staffToRemove);
                branch.setStaffMembers(staffMembers);
                return true;
            }
        }
        return false;
    }

    // The removeManager method is not currently needed as per previous discussions, but can be implemented similarly to removeStaff if needed.
    public void removeManager(Branch branch, Manager manager) {
        // Implement if required
    }
}
