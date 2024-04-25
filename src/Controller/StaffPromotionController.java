package Controller;

import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Helper.InputHelper;
import Interface.Controllers.IStaffManager;
import Interface.Controllers.IStaffPromotion;

/**
 * Controls the processes related to staff promotions and manager transfers within and between branches.
 */
public class StaffPromotionController implements IStaffPromotion {

    /**
     * Promotes a specified staff member to a manager within their current branch.
     *
     * @param branch The branch where the staff member is currently located.
     * @param name The name of the staff member to be promoted.
     */
    @Override
    public void promoteStaff(Branch branch, String name) {
        Staff staff = branch.getStaffByName(name);
        if (staff != null) {
            staff.setRole(Role.MANAGER);
            System.out.println("Staff promoted successfully");
        } else {
            System.out.println("Staff member not found");
        }
    }

    /**
     * Transfers a manager from one branch to another, ensuring that the staff-to-manager ratio
     * is maintained in the source branch.
     *
     * @param staffManager The staff manager controller to handle adding and removing staff.
     * @param branch The source branch from which the manager is being transferred.
     * @param targetBranch The target branch to which the manager is being transferred.
     */
    @Override
    public void transferManager(IStaffManager staffManager, Branch branch, Branch targetBranch) {
        int noOfManagers = branch.getNoOfManager();
        int noOfStaff = branch.getNoOfStaff();

        if (noOfManagers <= 1) {
            System.out.println("Transfer not possible. Source branch cannot have less than one manager.");
            return;
        }

        int managersNeeded = calculateManagersNeeded(noOfStaff);

        if (noOfManagers - 1 < managersNeeded) {
            System.out.println("Transfer not possible. It would break the staff-to-manager quota in the source branch.");
            return;
        }

        String name = InputHelper.getValidatedString("Enter name of Manager to transfer: ");
        Staff staff = branch.getStaffByName(name);

        if (staff != null && staffManager.addStaff(targetBranch, staff)) {
            staffManager.removeStaff(branch, staff.getName());
            System.out.println("Manager transferred successfully.");
        } else {
            System.out.println("Failed to transfer manager or manager not found.");
        }
    }

    /**
     * Calculates the number of managers needed based on the number of staff in a branch.
     *
     * @param noOfStaff The number of staff in the branch.
     * @return The number of managers required.
     */
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
