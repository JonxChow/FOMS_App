package ClassesWeDontNeedAnymore;

import java.util.Scanner;

/*
* Probably need to create interfaces for the methods in this class also
*/

public class BranchCreationUI{

    private final BranchCreationController branchCreationController;
    private final StaffCreationController staffCreationController;

    Scanner scanner = new Scanner(System.in);

    public BranchCreationUI(BranchCreationController branchCreationController, StaffCreationController staffCreationController) {
        this.branchCreationController = branchCreationController;
        this.staffCreationController = staffCreationController;
    }

    public void OpenBranch() {
        System.out.println("Enter Branch Name");
        String name = scanner.nextLine();

        System.out.println("Enter Branch Location");
        String location = scanner.nextLine();

        System.out.println("Enter No of Staff to add:");
        int noOfStaff = scanner.nextInt();

        this.branchCreationController.openBranch(name,location,noOfStaff);



    }

    public void closeBranch() {
        System.out.println("Enter Name of Branch to Close: ");
        String name = scanner.nextLine();
        this.branchCreationController.closeBranch(name);
    }

}
