package Interface.Admin;

import Entity.Branch.Branch;

public interface RemovePaymentMethod {
    void removePaymentMethod(Branch b, String type);
}
