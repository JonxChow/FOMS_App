package Controller;

import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.AddManager;
import Interface.Admin.EditManager;
import Interface.Admin.RemoveManager;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerCreationController implements AddManager, EditManager, RemoveManager {
    Scanner in = new Scanner(System.in);

    public void addManager(Branch branch){
        System.out.println("Enter Manager name: ");
        String name = in.nextLine();
        ArrayList<Staff> staffMembers = branch.getStaffMembers();
        staffMembers.add(new Manager(-1, name));
        branch.setStaffMembers(staffMembers);
    }

    @Override
    public void editManager() {

    }

    @Override
    public void removeManager() {

    }
}
