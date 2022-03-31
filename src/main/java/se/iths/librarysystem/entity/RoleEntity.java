package se.iths.librarysystem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Role is a required field")
    private String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private final List<UserEntity> personList = new ArrayList<>();

    public RoleEntity() {
    }

    public RoleEntity(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserEntity> getPersonList() {
        return Collections.unmodifiableList(personList);
    }

    public void addPerson(UserEntity person) {
        personList.add(person);
    }

    public void removePerson(UserEntity person) {
        personList.remove(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity that)) return false;
        return Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
