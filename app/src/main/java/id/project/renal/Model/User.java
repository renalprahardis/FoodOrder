package id.project.renal.Model;

/**
 * Created by ASUS on 26/02/2018.
 */

public class User {
    private String Name;
    private String Password;
    private String Phone;


    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    public User(){

    }

    public String getName() {
        return Name;
    }
    public String getPhone() {
        return Phone;
    }

    public void setName(String name) {
        Name = name;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
