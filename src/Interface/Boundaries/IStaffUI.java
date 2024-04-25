package Interface.Boundaries;

import Entity.Branch.Branch;
import Interface.Display.IDisplayMenu;

public interface IStaffUI  extends IDisplayMenu {
    void setBranch(Branch branch);
}
