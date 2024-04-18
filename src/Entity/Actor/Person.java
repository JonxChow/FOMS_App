package Entity.Actor;
public class Person {
    private int id;
    private String name;
    protected Role role;
    private int age;
    private Gender gender;

    private String userName, password;
    public Person(){
        id = -1;
        name = "Undefined";
        this.role = Role.PERSON;
    }

    public Person(int id, String name, int age, Gender gender){
        this.id = id;
        this.name = name;
        this.userName = "default";
        this.password = "default";
        this.role = Role.PERSON;
        this.gender = gender;
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

    public Boolean validatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
