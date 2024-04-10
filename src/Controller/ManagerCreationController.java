package Controller;

import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.AddManager;
import Interface.Admin.EditManager;
import Interface.Admin.RemoveManager;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ManagerCreationController implements AddManager, EditManager, RemoveManager {
    static Scanner in = new Scanner(System.in);

    public static void addManager(Branch b){
        System.out.println("Enter Manager name: ");
        String name = in.nextLine();
        ArrayList<Staff> staffMembers = b.getStaffMembers();
        staffMembers.add(new Manager(-1, name));
        b.setStaffMembers(staffMembers);
    }
}
