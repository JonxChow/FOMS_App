package Controller;

import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Controllers.ILoginController;

public class LoginController implements ILoginController {

    @Override
    public boolean validateAccount(Branch branch, String enteredUsername, String enteredPassword) {
        Staff staff = branch.getStaffByUsername(enteredUsername);
        if(staff == null) {
            System.out.println("Account doesn't exist");
            return false;
        } else {
            return staff.validateAccount(enteredUsername, enteredPassword);
        }
    }

    public Role getRoleByUsername(Branch branch, String username) {
        Staff staff = branch.getStaffByUsername(username);
        return staff.getStaffRole();
    }


    @Override
    public Staff getAvailableAccount(Branch branch, Role role) {
        return branch.getUnassignedStaff(role);
    }

    @Override
    public boolean setAccountDetails(Branch branch, String enteredUsername, String newUsername, String newPassword) {
        Staff staff = branch.getStaffByUsername(enteredUsername);
        if(staff == null) return false;

        staff.setUsername(newUsername);
        staff.setPassword(newPassword);

        return true;
    }

    @Override
    public void claimAccount(Branch branch, Staff staff) {
        branch.getStaffByName(staff.getName()).setId();
    }

}
