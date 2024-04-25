package Interface.Boundaries;

import Entity.Branch.Branch;

public interface IStaffActionsUI {

    Branch getBranch();

    void addStaffIndividual(Branch branch);

    void addManagerIndividual(Branch branch);

    void removeStaff();
}
