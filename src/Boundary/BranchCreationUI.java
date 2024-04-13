package Boundary;

import Controller.BranchCreationController;

import java.util.Scanner;

/*
* Probably need to create interfaces for the methods in this class also
*/

public class BranchCreationUI{

    private final BranchCreationController branchCreationController;

    Scanner scanner = new Scanner(System.in);

    public BranchCreationUI(BranchCreationController branchCreationController) {
        this.branchCreationController = branchCreationController;
    }

    public void OpenBranch() {
        System.out.println("Enter Branch Name");
        String name = scanner.nextLine();
        System.out.println("Enter Branch Location");
        String location = scanner.nextLine();

        this.branchCreationController.openBranch(name,location);
    }

    public void closeBranch() {
        System.out.println("Enter Name of Branch to Close: ");
        String name = scanner.nextLine();
        this.branchCreationController.closeBranch(name);
    }

}
