package se.iths.librarysystem.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserWithRole {

    private Long id;
    private String firstname;
    private String lastname;
    private String ssn;
    private String email;
    private String phoneNumber;
    private String address;
    private Set<Role> roles = new HashSet<>();

    public UserWithRole() {
    }

    public UserWithRole(String firstname, String lastname, String ssn, String email, String phoneNumber,
                        String address, Set<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roles = roles;
    }

    public UserWithRole(String firstname, String lastname, String ssn, String email, String role, String  phoneNumber) {
        this(firstname, lastname, ssn, email, role, phoneNumber, Set.of());
    }

    public UserWithRole(String firstname, String lastname, String ssn, String email, String role) {
        this(firstname, lastname, ssn, email, role, "", Set.of());
    }


    public UserWithRole(String firstname, String lastname, String ssn, String email) {
        this(firstname, lastname, ssn, email,"", "", Set.of());
    }

    public Long getId() {
        return id;
    }

    public UserWithRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserWithRole setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserWithRole setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getSsn() {
        return ssn;
    }

    public UserWithRole setSsn(String dob) {
        this.ssn = dob;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserWithRole setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserWithRole setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserWithRole setAddress(String address) {
        this.address = address;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public UserWithRole setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserWithRole addRole(Role role) {
        roles.add(role);
        return this;
    }

    public UserWithRole removeRole(Role role) {
        roles.remove(role);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserWithRole that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname)
               && Objects.equals(lastname, that.lastname) && Objects.equals(ssn, that.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, ssn);
    }
}
