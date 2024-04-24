package Boundary;


import Entity.Actor.Gender;
import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Controllers.IStaffManager;
import Interface.Display.IDisplayMenu;
import Entity.Branch.Branch;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffActionsUI implements IDisplayMenu {

    private final IStaffManager staffManager;
    private final IAllBranches allBranches;
    private final Scanner scanner = new Scanner(System.in);

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
                    addStaff(getBranch());
                    break;

                case 2:
                    removeStaff();
                    break;
            }
        } while(choice < 4);
    }

    public Branch getBranch(){
        String name = InputHelper.getValidatedString("Enter Branch to Edit: ");
        return allBranches.getBranchByName(name);
    }

    //for indic addStaff call getBranchUI
    public void addStaff(Branch branch) {
        ArrayList<Staff> staffMembers = new ArrayList<>();
        Gender gender;

        int max = 15 - branch.getStaffMembers().size();
        if(max == 0) {
            System.out.println("Maximum Staff Capacity Reached");
            return;
        }

        int noOfStaff = InputHelper.getValidatedInt("Enter No of Staff to Add: ", 1, max);
        //init all staff members
        for(int i = 0; i < noOfStaff; i++) {
            String staffName = InputHelper.getValidatedString("Enter staff name: ");
            int staffGender = InputHelper.getValidatedInt("Enter staff gender 0(female), 1(male)", 0, 1);
            if(staffGender == 0) gender = Gender.FEMALE;
            else gender = Gender.MALE;
            int staffAge = InputHelper.getValidatedInt("Enter staff age: ", 0, 99);

            //ArrayList<Staff> staffMembers = branch.getStaffMembers();
            staffMembers.add(new Staff(-1, staffName, staffAge, gender));
        }
        //init the required number of managers
        int managers = (int) Math.ceil(noOfStaff / 4.0);
        managers = Math.min(managers, 3);

        for(int i=0; i < managers; i++){
            String staffName = InputHelper.getValidatedString("Enter manager name: ");
            int staffGender = InputHelper.getValidatedInt("Enter staff gender 0(female), 1(male)", 0, 1);
            if(staffGender == 0) gender = Gender.FEMALE;
            else gender = Gender.MALE;
            int staffAge = InputHelper.getValidatedInt("Enter staff age: ", 0, 99);

            //ArrayList<Staff> staffMembers = branch.getStaffMembers();
            staffMembers.add(new Manager(-1, staffName, staffAge, gender));
        }
        if(staffManager.addStaff(branch, staffMembers)) {
            System.out.println("Staff added successfully");
        } else {
            System.out.println("Unable to add staff");
        }
    }

    public void AddManager(Branch branch) {
        //staff number check
        int totalStaff = branch.getStaffMembers().size();
        int availableSlots =  - totalStaff;
        if(totalStaff == 0) {
            System.out.println("Maximum staff quota reached. Unable to add more");
            return;
        }
        //no need to check if enough managers here, do that in addStaff instead

        //ask for no of managers to add
        System.out.println("You are able to add " + availableSlots + "number of managers");
        int noOfManagers = InputHelper.getValidatedInt("Enter no. of managers to add", 1, availableSlots);
        //check against total staff in branch
        //loop to add all managers into staff list
        //use staffManager.addStaff to add using staff list

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
