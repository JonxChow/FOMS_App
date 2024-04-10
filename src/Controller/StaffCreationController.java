package Controller;

import Entity.Actor.Person;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.AddStaff;
import Interface.Admin.RemoveStaff;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class StaffCreationController implements AddStaff, RemoveStaff {
    static Scanner in = new Scanner(System.in);

    public static void addStaff(Branch b){
        System.out.println("Enter staff name: ");
        String name = in.nextLine();
        ArrayList<Staff> staffMembers = b.getStaffMembers();
        staffMembers.add(new Staff(-1, name));
        b.setStaffMembers(staffMembers);
    }

    @Override
    public void removeStaff(Person s) {

    }
}
