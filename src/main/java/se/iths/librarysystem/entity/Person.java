package se.iths.librarysystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname is a required field")
    private String firstname;

    @NotBlank(message = "Lastname is a required field")
    private String lastname;

    @NotBlank(message = "Date of birth is a required field")
    private String dob;

    @Email(message = "Email address must be valid")
    private String email;

    private String phoneNumber;
    private String address;

    public Person() {
    }

    public Person(String firstname, String lastname, String dob,
                  String phoneNumber, String email, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Person(String firstname, String lastname, String dob, String phoneNumber, String email) {
        this(firstname, lastname, dob, phoneNumber, email, "");
    }

    public Person(String firstname, String lastname, String dob, String phoneNumber) {
        this(firstname, lastname, dob, phoneNumber, "", "");
    }

    public Person(String firstname, String lastname, String dob) {
        this(firstname, lastname, dob, "", "", "");
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(firstname, person.firstname) && Objects.equals(lastname, person.lastname)
               && Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, dob);
    }
}
