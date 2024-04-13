package Entity.Lists;

import Entity.Branch.Branch;

import java.util.ArrayList;
import java.util.List;

public class AllBranches {

    private final List<Branch> allBranches;

    public AllBranches() {
        this.allBranches = new ArrayList<>();
    }

    public void addBranch(Branch branch) {
        allBranches.add(branch);
    }

    public List<Branch> getAllBranches() {
        return allBranches;
    }

    public Branch getBranchByName (String name) {
        for (Branch allBranch : allBranches) {
            if (allBranch.getBranchName().equals(name)) {
                return allBranch;
            }
        }

        return null;
    }
}
