package se.iths.librarysystem.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

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
    private String password;
    private String username;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private RoomEntity room;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<BookEntity> books = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String firstname, String lastname, String ssn, String email,
                      String phoneNumber, String address, String password, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.username = createUsername(firstname, lastname, username);
    }

    public UserEntity(String firstname, String lastname, String ssn, String email,
                      String phoneNumber, String password, String username) {
        this(firstname, lastname, ssn, email, phoneNumber, "", password, username);
    }

    public UserEntity(String firstname, String lastname, String ssn, String email, String password) {
        this(firstname, lastname, ssn, email, "", "", password, "");
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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.addUser(this);
    }

    public void removeRole(RoleEntity role) {
       this.roles.remove(role);
       role.removeUser(this);
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public List<BookEntity> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void addBook(BookEntity book) {
        books.add(book);
    }

    public void removeBook(BookEntity book) {
        books.remove(book);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String createUsername(String firstname, String lastname, String username) {
        return username == null || username.equals("") ? firstname + lastname : username;
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
