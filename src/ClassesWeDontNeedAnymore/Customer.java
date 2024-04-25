package ClassesWeDontNeedAnymore;

import Entity.Actor.Gender;
import Entity.Actor.Person;
import Entity.Actor.Role;
import Entity.Order.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private String username = "default";
    private String password = "password";
    private List<Order> orderList = new ArrayList<>();

    public Customer(int id, String name, int age, Gender gender) {
        super(id, name, age, gender);
        role = Role.CUSTOMER;
        List<Order> orderList = new ArrayList<>();
    }

    public List<Order> getOrderList(){return orderList;}
}
