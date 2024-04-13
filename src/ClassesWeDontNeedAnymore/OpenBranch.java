package ClassesWeDontNeedAnymore;

import Entity.Branch.Branch;
import Entity.Lists.AllBranches;

public interface OpenBranch {

    //public static void openBranch(AllBranches allBranches) {}

    void openBranch(String name, String location, int noOfStaff);
}
