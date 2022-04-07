package se.iths.librarysystem.dto;

public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private String ssn;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;

    public User() {
    }

    public User(String firstname, String lastname, String ssn, String email,
                String phoneNumber, String address, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.username = username;
    }

    public User(String firstname, String lastname, String ssn,
                String email, String  phoneNumber, String username) {
        this(firstname, lastname, ssn, email, phoneNumber, "", username);
    }

    public User(String firstname, String lastname, String ssn, String email, String username) {
        this(firstname, lastname, ssn, email, "", "", username);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String dob) {
        this.ssn = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
