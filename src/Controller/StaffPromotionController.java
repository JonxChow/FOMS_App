package Controller;

import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Controllers.IStaffManager;
import Interface.Controllers.IStaffPromotion;

public class StaffPromotionController implements IStaffPromotion {
    @Override
    public void promoteStaff(Branch branch, String name) {
        Staff staff = branch.getStaffByName(name);
        staff.setRole(Role.MANAGER);
        System.out.println("Staff promoted successfully");
    }

    @Override
    public void transferManager(IStaffManager staffManager, Branch branch, Branch targetBranch) {
        //check no of staff and managers
//        int noOfManagers = branch.getNumberOfStaffByRole(Role.MANAGER);
//        int noOfStaff = branch.getNumberOfStaffByRole(Role.STAFF);
        int noOfManagers = branch.getNoOfManager();
        int noOfStaff = branch.getNoOfStaff();
        //if transferring manager breaks quota restriction, reject transfer

        if (noOfManagers <= 1) {
            System.out.println("Transfer not possible. Source branch cannot have less than one manager.");
            return;
        }

        // Calculate managers needed based on staff count
        int managersNeeded = calculateManagersNeeded(noOfStaff);

        // Check if transferring a manager breaks the quota restriction.
        if (noOfManagers - 1 < managersNeeded) {
            System.out.println("Transfer not possible. It would break the staff-to-manager quota in the source branch.");
            return;
        }

        //Transfer the manager
        String name = InputHelper.getValidatedString("Enter name of Manager to transfer: ");
        Staff staff = branch.getStaffByName(name);
        if(staffManager.addStaff(targetBranch, staff)) {
            staffManager.removeStaff(branch, staff.getName());
            System.out.println("Manager transferred successfully.");
        } else {
            System.out.println("Failed to transfer manager");
        }
    }

    private int calculateManagersNeeded(int noOfStaff) {
        if (noOfStaff <= 4) {
            return 1;
        } else if (noOfStaff <= 8) {
            return 2;
        } else {
            return 3; // For 9-15 staff members
        }
    }
}