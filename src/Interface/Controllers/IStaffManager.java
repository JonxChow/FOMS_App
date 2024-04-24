package Interface.Controllers;

import Entity.Actor.Staff;
import Entity.Branch.Branch;
import  Entity.Actor.Manager;

import java.util.ArrayList;

public interface IStaffManager {

    boolean addStaff(Branch branch, Staff newStaff);

    public boolean addManager(Branch branch, Manager newManager);

    public boolean removeStaff(Branch branch, String staffName);

    //void addStaffIndividual(Branch branch);

    //void addManagerIndividual(Branch branch);

    //void removeManger(Branch branch, Manager manager);

}
