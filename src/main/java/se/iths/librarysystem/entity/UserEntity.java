package se.iths.librarysystem.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname is a required field")
    private String firstname;

    @NotBlank(message = "Lastname is a required field")
    private String lastname;

    @NotBlank(message = "Social security number is a required field")
    private String ssn;

    @Email(message = "Email address must be valid")
    @NotBlank(message = "Email address is a required field")
    private String email;

    private String phoneNumber;
    private String address;

    @ManyToOne
    private RoleEntity role;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private RoomEntity room;

    public UserEntity() {

    }

    public UserEntity(String firstname, String lastname, String ssn,
                      String email, String phoneNumber, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public UserEntity(String firstname, String lastname, String ssn, String email, String  phoneNumber) {
        this(firstname, lastname, ssn, email, phoneNumber, "");
    }

    public UserEntity(String firstname, String lastname, String ssn, String email) {
        this(firstname, lastname, ssn, email, "", "");
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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public UserEntity setRoom(RoomEntity room) {
        this.room = room;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity userEntity)) return false;
        return Objects.equals(firstname, userEntity.firstname) && Objects.equals(lastname, userEntity.lastname)
               && Objects.equals(ssn, userEntity.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, ssn);
    }
}
