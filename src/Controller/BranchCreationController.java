package Controller;

import Entity.Branch.Branch;
import Entity.Lists.AllBranches;
import Interface.Admin.CloseBranch;
import Interface.Admin.OpenBranch;

import java.util.List;
import java.util.Scanner;

public class BranchCreationController implements OpenBranch, CloseBranch {
    static Scanner scanner = new Scanner(System.in);
    static int noOfStaff = 0;

    public static void openBranch(AllBranches allBranches){
        //Create new branch using name and location
        System.out.println("Enter Branch details: ");
        System.out.println("Enter branch name: ");
        String bName = scanner.nextLine();
        System.out.println("Enter branch location: ");
        String location = scanner.nextLine();
        Branch b = new Branch(bName, location);
        AllBranches.addBranch(b);

        //check if initial add staff < 15
        do {
            System.out.println("Enter number of staff members: ");
            noOfStaff = scanner.nextInt();
        } while(noOfStaff > 12);

        int noOfManager = (int)Math.ceil(noOfStaff/4);
        System.out.println("For " + noOfStaff + "you would need " + noOfManager + "managers and " + noOfStaff);
        System.out.println("Please create " + noOfStaff);
        //perform adding staff
        for(int i=0; i<noOfStaff; i++){
            StaffCreationController.addStaff(b);
        }
        System.out.println("Please create " + noOfManager);
        for(int i=0; i<noOfManager; i++){
            ManagerCreationController.addManager(b);
        }
    }

    //remove branch by name
    public void closeBranch(String bName) {
        List<Branch> branchList = AllBranches.getAllBranches();
        for(Branch b : branchList){
            if(b.getBranchName().compareTo(bName) == 0){
                branchList.remove(b);
                //call excel file to remove branch
                return;
            }
        }
        System.out.println("Invalid branch name!");
        System.out.println("Deletion failed. Please try again");
    }
}
