package Controller;

import Entity.Actor.Person;

public class LoginController {

//    public boolean isAuthenticated(Person person, String enteredUserName, String enteredPassword) {
//        return person.getUserName().equals(enteredUserName) && person.getPassword().equals(enteredPassword);
//    }

    public boolean renameAccount(Person person, String enteredUserName, String enteredPassword) {
        try {
            person.setUserName(enteredUserName);
            person.setPassword(enteredPassword);
            return true;
        } catch (Exception e) {
            System.out.println("Account creation failed");
            return false;
        }
    }
}
