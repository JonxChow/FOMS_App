package Entity.Actor;
public class Person {
    private int id;
    private String name;
    protected Role role;

    private String userName, password;
    public Person(){
        id = -1;
        name = "Undefined";
        this.role = Role.PERSON;
    }

    public Person(int id, String name){
        this.id = id;
        this.name = name;
        this.userName = "default";
        this.password = "default";
        role = Role.PERSON;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }
}