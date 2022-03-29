package se.iths.librarysystem.dto;

public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private String ssn;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;

    public User() {
    }

    public User(String firstname, String lastname, String ssn,
                String email, String role, String phoneNumber, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public User(String firstname, String lastname, String ssn, String email, String role, String  phoneNumber) {
        this(firstname, lastname, ssn, email, role, phoneNumber, "");
    }

    public User(String firstname, String lastname, String ssn, String email, String role) {
        this(firstname, lastname, ssn, email, role, "", "");
    }


    public User(String firstname, String lastname, String ssn, String email) {
        this(firstname, lastname, ssn, email,"", "", "");
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
