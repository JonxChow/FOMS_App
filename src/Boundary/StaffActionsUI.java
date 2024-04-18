package Boundary;


import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;
import Entity.Branch.Branch;

public class StaffActionsUI implements IDisplayMenu {

    private final IStaffManager staffManager;
    private final IAllBranches allBranches;

    public StaffActionsUI (IAllBranches allBranches, IStaffManager staffManager) {
        this.staffManager = staffManager;
        this.allBranches = allBranches;
    }



    @Override
    public void displayMenu() {
        int choice;

        System.out.println("1: Add Staff");
        System.out.println("2: Remove Staff");
        System.out.println("3: Edit Staff");
        System.out.println("4: Exit");

        do{
            choice = InputHelper.getValidatedInt("Select Option: ", 1, 4);

            switch (choice) {
                case 1:
                    addStaff();
                    break;

                case 2:
                    removeStaff();
                    break;
            }
        } while(choice < 4);
    }

    private void addStaff() {
        String name = InputHelper.getValidatedString("Enter Branch to Edit: ");
        Branch branch = allBranches.getBranchByName(name);

        int max = 15 - branch.getStaffMembers().size();
        if(max == 0) {
            System.out.println("Maximum Staff Capacity Reached");
            return;
        }

        int noOfStaff = InputHelper.getValidatedInt("Enter No of Staff to Add: ", 1, max);
        if(staffManager.addStaff(branch, noOfStaff)) {
            System.out.println("Staff added successfully");
        } else {
            System.out.println("Unable to add staff");
        }
    }

    private void removeStaff() {
        String name = InputHelper.getValidatedString("Enter Branch to Edit: ");
        Branch branch = allBranches.getBranchByName(name);

        //need to make sure all staff objects are assigned
        String staffName = InputHelper.getValidatedString("Enter Name of Staff you wish to remove: ");

        if(staffManager.removeStaff(branch, staffName)) {
            System.out.println("Staff removed successfully");
        } else {
            System.out.println("Unable to remove staff");
        }
    }
}
