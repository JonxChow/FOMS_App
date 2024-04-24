package Interface.Admin;

import Entity.Branch.Branch;

import java.util.List;

public interface IAllBranches {

    void addBranch(Branch branch);

    List<Branch> getAllBranches();

    Branch getBranchByName (String name);

    void removeBranch(Branch branch);
}
