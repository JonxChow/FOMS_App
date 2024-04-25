package Controller;

import Entity.Actor.Gender;
import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Controllers.IStaffManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages staff operations within a branch, including adding, removing, and managing both staff and managers.
 * This controller facilitates the dynamic management of branch personnel, ensuring that staff records
 * are accurately maintained within branches.
 */
public class StaffManagementController implements IStaffManager {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Adds a staff member to a specified branch.
     * This method updates the branch's staff list by adding a new staff member and ensures
     * the staff list in the branch reflects this addition.
     *
     * @param branch The branch to which the staff member will be added.
     * @param newStaff The staff member to be added to the branch.
     * @return true if the staff member was added successfully, false otherwise.
     */
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

    /**
     * Adds a manager to a specified branch.
     * This method updates the branch's staff list by adding a new manager and ensures
     * the staff list in the branch is appropriately updated.
     *
     * @param branch The branch to which the manager will be added.
     * @param newManager The manager to be added to the branch.
     * @return true if the manager was added successfully, false otherwise.
     */
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

    /**
     * Removes a staff member from a specified branch by name.
     * This method searches for and removes a staff member from the branch's staff list based on the provided name.
     *
     * @param branch The branch from which the staff member will be removed.
     * @param staffName The name of the staff member to be removed.
     * @return true if the staff member was successfully removed, false otherwise.
     */
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
}
