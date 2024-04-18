package Interface.Controllers;

import Entity.Branch.Branch;
import  Entity.Actor.Manager;

public interface IStaffManager {

    boolean addStaff(Branch branch, int noOfStaff);

    void addManager(Branch branch, int noOfManagers);

    boolean removeStaff(Branch branch, String staffName);

    void removeManger(Branch branch, Manager manager);

}
