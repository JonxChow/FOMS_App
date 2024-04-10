package Entity.Lists;

import Entity.Branch.Branch;

import java.util.ArrayList;
import java.util.List;

public class AllBranches {

    private static List<Branch> allBranches;

    public AllBranches() {
        this.allBranches = new ArrayList<>();
    }

    public static void addBranch(Branch branch) {
        allBranches.add(branch);
    }

    public static List<Branch> getAllBranches() {
        return allBranches;
    }
}
