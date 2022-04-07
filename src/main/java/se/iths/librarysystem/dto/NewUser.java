package se.iths.librarysystem.dto;

public class NewUser extends User {

    private String password;

    public NewUser() {
    }

    public NewUser(String firstname, String lastname, String ssn, String email, String phoneNumber, String address,
                   String username, String password) {
        super(firstname, lastname, ssn, email, phoneNumber, address, username);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
