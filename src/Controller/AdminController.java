package Controller;

import ClassesWeDontNeedAnymore.AddStaff;
import ClassesWeDontNeedAnymore.CloseBranch;
import ClassesWeDontNeedAnymore.OpenBranch;
import ClassesWeDontNeedAnymore.RemoveStaff;
import Interface.Admin.*;

public abstract class AdminController extends PaymentController implements AddStaff, CloseBranch, EditStaff, OpenBranch, PromoteStaff, RemoveStaff, TransferStaff {
}
