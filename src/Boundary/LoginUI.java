package Boundary;

import Helper.InputHelper;
import Interface.Display.IDisplayMenu;

import java.util.Scanner;

public class LoginUI implements IDisplayMenu {
    private AdminUI adminUI;
    private StaffUI staffUI;
    private ManagerUI managerUI;

    //private AuthenticationService authenticator;

    public LoginUI() {

    }


    @Override
    public void displayMenu() {
        String username = InputHelper.getValidatedString("Enter Username: ");
        String password = InputHelper.getValidatedString("Enter Password: ");

        //Person person = authenticator.authenticate(username, password);

//        if (person !null){
//            switch (person.getRole()) {
//                case ADMIN:
//                    adminUI.displayMenu();
//                    break;
//
//                case MANAGER:
//                    managerUI.displayMenu();
//                    break;
//
//                case STAFF:
//                    staffUI.displayMenu();
//                    break;
//
//                default:
//                    System.out.println("Invalid role");
//                    break;
//            }
//        } else{
//            System.out.println("Log in failed. Username or Password Incorrect!");
//        }
    }
}
