package ClassesWeDontNeedAnymore;

import Entity.Lists.AllBranches;

import java.util.Scanner;

public class BranchControlUI {

    private StaffCreationController staffCreationController;
    private AllBranches allBranches;

    Scanner scanner = new Scanner(System.in);

    public BranchControlUI(StaffCreationController staffCreationController, AllBranches allBranches) {
        this.staffCreationController = staffCreationController;
        this.allBranches = allBranches;
    }

    public void addStaff() {
        System.out.println("Enter Branch to add Staff to: ");
        String name = scanner.nextLine();
        this.staffCreationController.addStaff(allBranches.getBranchByName(name));
    }
}
