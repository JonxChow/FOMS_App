package Controller;

import Interface.Manager.AddMenuItem;
import Interface.Manager.EditMenuItem;
import Interface.Manager.ListStaff;
import Interface.Manager.RemoveMenuItem;

import java.util.List;

public abstract class ManagerController extends StaffController implements AddMenuItem, EditMenuItem, ListStaff, RemoveMenuItem {
}
