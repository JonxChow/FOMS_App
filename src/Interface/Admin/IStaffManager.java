package Interface.Admin;

import Entity.Branch.Branch;
import  Entity.Actor.Staff;
import  Entity.Actor.Manager;

public interface IStaffManager {

    void addStaff(Branch branch, int noOfStaff);

    void addManager(Branch branch, int noOfManagers);

    void removeStaff(Branch branch, Staff staff);

    void removeManger(Branch branch, Manager manager);


}
