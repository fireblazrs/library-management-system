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

    public UserEntity(String firstname, String lastname, String ssn, String email, String password, String username) {
        this(firstname, lastname, ssn, email, "", "", password, username);
    }

    public UserEntity(String firstname, String lastname, String ssn, String password, String username) {
        this(firstname, lastname, ssn, "", "", "", password, username);
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserEntity setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserEntity setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getSsn() {
        return ssn;
    }

    public UserEntity setSsn(String dob) {
        this.ssn = dob;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity addRole(RoleEntity role) {
        this.roles.add(role);
        role.addUser(this);
        return this;
    }

    public void removeRole(RoleEntity role) {
       this.roles.remove(role);
       role.removeUser(this);
    }

    public RoomEntity getRoom() {
        return room;
    }

    public UserEntity setRoom(RoomEntity room) {
        this.room = room;
        return this;
    }

    public List<BookEntity> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public UserEntity addBooks(Set<BookEntity> books) {
        this.books.addAll(books);
        return this;
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

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
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
