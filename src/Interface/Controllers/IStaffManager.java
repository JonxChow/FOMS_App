package Interface.Controllers;

import Entity.Actor.Staff;
import Entity.Branch.Branch;
import  Entity.Actor.Manager;

import java.util.ArrayList;

public interface IStaffManager {

    boolean addStaff(Branch branch, ArrayList<Staff> staffMembers);

    void addManager(Branch branch, int noOfManagers);

    boolean removeStaff(Branch branch, String staffName);

    void removeManger(Branch branch, Manager manager);

}
