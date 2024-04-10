import Controller.LoginController;

import java.util.Scanner;

public class Main {

    LoginController login = new LoginController();
    public static void main(String[] args) {
        System.out.println("Welcome to FOMS");
        System.out.println("Press 1 to change your details");
        System.out.println("Press 2 to login");

        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter your branch id");
                //get branch list
                // see if objects are still unassigned
                //change their username and password
                //set the object to assigned

        }
    }
}