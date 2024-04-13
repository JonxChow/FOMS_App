package Controller;

import Entity.Branch.Branch;
import Entity.Lists.AllBranches;
import Interface.Admin.CloseBranch;
import Interface.Admin.OpenBranch;

import java.util.List;
import java.util.Scanner;

public class BranchCreationController implements OpenBranch, CloseBranch {

    private final AllBranches allBranches;
    private final StaffCreationController staffCreationController;
    private final ManagerCreationController managerCreationController;
    Scanner scanner = new Scanner(System.in);
    int noOfStaff = 0;

    public BranchCreationController(AllBranches allBranches, StaffCreationController staffCreationController, ManagerCreationController managerCreationController) {
        this.allBranches = allBranches;
        this.staffCreationController = staffCreationController;
        this.managerCreationController = managerCreationController;
    }

    public void openBranch(String name, String location){
        //Create new branch using name and location
//        System.out.println("Enter Branch details: ");
//        System.out.println("Enter branch name: ");
//        String bName = scanner.nextLine();
//        System.out.println("Enter branch location: ");
//        String location = scanner.nextLine();
//        Branch b = new Branch(bName, location);
//        AllBranches.addBranch(b);

        Branch newbranch = new Branch(name, location);
        this.allBranches.addBranch(newbranch);

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
            this.staffCreationController.addStaff(newbranch);
        }
        System.out.println("Please create " + noOfManager);
        for(int i=0; i<noOfManager; i++){
            this.managerCreationController.addManager(newbranch);
        }
    }

    //remove branch by name
    public void closeBranch(String bName) {
        List<Branch> branchList = this.allBranches.getAllBranches();
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
