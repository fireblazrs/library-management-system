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

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getSsn() {
        return ssn;
    }

    public User setSsn(String dob) {
        this.ssn = dob;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }
}
