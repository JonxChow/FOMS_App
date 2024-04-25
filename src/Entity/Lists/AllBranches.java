package Entity.Lists;

import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code AllBranches} class implements the {@code IAllBranches} interface and provides a way to manage and store
 * a collection of {@code Branch} objects. It offers functionality to add, remove, retrieve, and check branches,
 * as well as to obtain a list of all staff members across all branches.
 */
public class AllBranches implements IAllBranches, Serializable {
    private static final long serialVersionUID = 1L;  // Ensure version compatibility

    private final List<Branch> allBranches;

    /**
     * Constructs an {@code AllBranches} instance with an empty list of branches.
     */
    public AllBranches() {
        this.allBranches = new ArrayList<>();
    }

    /**
     * Adds a new branch to the list of branches.
     *
     * @param branch The {@code Branch} to be added.
     */
    public void addBranch(Branch branch) {
        allBranches.add(branch);
    }

    /**
     * Returns a list of all branches.
     *
     * @return A {@code List} of all {@code Branch} instances managed by this class.
     */
    public List<Branch> getAllBranches() {
        return allBranches;
    }

    /**
     * Retrieves a branch by its name.
     *
     * @param name The name of the branch to retrieve.
     * @return The {@code Branch} with the specified name, or {@code null} if not found.
     */
    public Branch getBranchByName (String name) {
        for (Branch allBranch : allBranches) {
            if (allBranch.getBranchName().equals(name)) {
                return allBranch;
            }
        }

        return null;
    }

    /**
     * Removes the specified branch from the list.
     *
     * @param branch The {@code Branch} to remove.
     */
    @Override
    public void removeBranch(Branch branch) {
        allBranches.remove(branch);
    }

    /**
     * Returns a list of all staff members across all branches.
     *
     * @return A {@code List} of {@code Staff} members.
     */
    @Override
    public List<Staff> getAllStaff() {
        return allBranches.stream()
                .flatMap(branch -> branch.getStaffMembers().stream())
                .collect(Collectors.toList());
    }

    /**
     * Prints the names of all branches to the standard output.
     */
    @Override
    public void printBranches() {
        for (Branch allBranch : allBranches) {
            System.out.println(allBranch.getBranchName());
        }
    }

    /**
     * Checks if a branch with the specified name exists.
     *
     * @param branchName The name of the branch to check for existence.
     * @return {@code true} if the branch exists, {@code false} otherwise.
     */
    @Override
    public boolean branchExists(String branchName) {
        Branch branch = getBranchByName(branchName);
        return branch != null;
    }
}
