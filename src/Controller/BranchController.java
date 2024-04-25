package Controller;

import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IStaffActionsUI;
import Interface.Controllers.IBranchController;

/**
 * Manages branch operations within the organization, such as creation, initialization, and closure.
 * This controller interfaces with the system's branch records and staff management UI to ensure
 * branches are properly set up and maintained.
 */
public class BranchController implements IBranchController {
    private final IAllBranches allBranches;
    private final IStaffActionsUI staffActionsUI;

    /**
     * Constructs a BranchController with dependencies.
     *
     * @param allBranches Interface to all branches' data.
     * @param staffActionsUI Interface to the staff actions user interface.
     */
    public BranchController(IAllBranches allBranches, IStaffActionsUI staffActionsUI){
        this.allBranches = allBranches;
        this.staffActionsUI = staffActionsUI;
    }

    /**
     * Creates a new branch if a branch with the same name does not already exist.
     *
     * @param name The name of the new branch.
     * @param location The location of the new branch.
     * @return A new Branch object if creation is successful, null if a branch with the same name exists.
     */
    public Branch createBranch(String name, String location) {
        if(allBranches.branchExists(name)) {
            System.out.println("Branch with this name already exists");
            return null;
        }
        Branch newBranch = new Branch(name, location);
        allBranches.addBranch(newBranch);
        return newBranch;
    }

    /**
     * Initializes a branch with a specified number of staff and managers, ensuring correct staffing ratios.
     *
     * @param branch The branch to be initialized.
     * @param numberOfStaff The number of staff to add to the branch.
     * @param numberOfManagers The number of managers to add to the branch.
     */
    public void initializeBranchWithStaff(Branch branch, int numberOfStaff, int numberOfManagers) {
        for (int i = 0; i < numberOfManagers; i++) {
            staffActionsUI.addManagerIndividual(branch);
        }
        for (int i = 0; i < numberOfStaff; i++) {
            staffActionsUI.addStaffIndividual(branch);
        }
    }

    /**
     * Closes a branch by name, removing it from the system if it exists.
     *
     * @param name The name of the branch to close.
     * @return true if the branch was successfully removed, false otherwise.
     */
    public boolean closeBranch(String name) {
        Branch branch = allBranches.getBranchByName(name);
        if (branch != null) {
            allBranches.removeBranch(branch);
            return true;
        }
        return false;
    }
}
