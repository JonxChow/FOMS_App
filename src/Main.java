
import Boundary.BranchCreationUI;
import Controller.BranchCreationController;
import Controller.LoginController;
import Controller.ManagerCreationController;
import Controller.StaffCreationController;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Entity.Lists.AllBranches;
import Entity.Menu.Menu;
import Entity.Order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LoginController login = new LoginController();

        //For staff actions
        StaffCreationController staffCreationController = new StaffCreationController();
        ManagerCreationController managerCreationController = new ManagerCreationController();

        //Initialising objects for Branch creation
        AllBranches allBranches = new AllBranches();
        BranchCreationController branchCreationController = new BranchCreationController(allBranches, staffCreationController, managerCreationController);
        BranchCreationUI branchUI = new BranchCreationUI(branchCreationController);




        System.out.println("Welcome to FOMS");
        System.out.println("Press 1 to change your details");
        System.out.println("Press 2 to login");
        System.out.println("Press 3 to create new Branch(Admin)");

        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter your branch id");
                //get branch list
                // see if objects are still unassigned
                //change their username and password
                //set the object to assigned
            case 3:
                branchUI.OpenBranch();

        }
    }
}