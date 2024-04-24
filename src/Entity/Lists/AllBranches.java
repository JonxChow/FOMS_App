package Entity.Lists;

import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllBranches implements IAllBranches {

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

    @Override
    public void removeBranch(Branch branch) {

    }

    @Override
    public List<Staff> getAllStaff() {
        return allBranches.stream()
                .flatMap(branch -> branch.getStaffMembers().stream())
                .collect(Collectors.toList());
    }

    @Override
    public void printBranches() {
        for (Branch allBranch : allBranches) {
            System.out.println(allBranch.getBranchName());
        }
    }
}
