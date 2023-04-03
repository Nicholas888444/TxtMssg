import java.io.Serializable;

public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private String loginID;
    private String password;

    //Don't need sender or reveiver
    public Person(String firstName, String lastName, String loginID, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginID = loginID;
        this.password = password;
    }

    public Person(){}

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginID() {
        return loginID;
    }
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}




