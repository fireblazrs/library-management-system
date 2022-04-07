package se.iths.librarysystem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Firstname is a required field")
    private String firstName;
    @NotBlank(message = "Lastname is a required field")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "book_enrolled",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")

    )
    private List<BookEntity> enrolledBooks = new ArrayList<>();

    public AuthorEntity() {
    }

    public AuthorEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<BookEntity> getEnrolledBooks() {
        return enrolledBooks;
    }

    public void setEnrolledBooks(List<BookEntity> enrolledBooks) {
        this.enrolledBooks = enrolledBooks;
    }

    public List<BookEntity> getBookEntities() {
        return Collections.unmodifiableList(enrolledBooks);
    }

    public void addBookEntity(BookEntity bookEntity) {
        enrolledBooks.add(bookEntity);
    }

    public void removeBookEntity(BookEntity bookEntity) {
        enrolledBooks.remove(bookEntity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
