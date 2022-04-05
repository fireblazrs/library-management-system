package se.iths.librarysystem.entity;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is a required field")
    private String title;
    private String subtitle;
    @NotBlank(message = "Edition is a required field")
    private String edition;
    @NotNull(message = "Print-date is a required field")
    private Date printed;

    @NotBlank(message = "ISBN is a required field")
    private String isbn;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity borrower;

    @Transient
    @ManyToMany(mappedBy = "enrolledBooks" )
    private List<AuthorEntity> authors = new ArrayList<>();

    @Transient
    @OneToMany(mappedBy = "bookEntity1")
    List<BookFormatEntity> bookFormatEntities = new ArrayList<>();

    @Transient
    @OneToMany(mappedBy = "bookEntity2")
    List<GenreEntity> genreEntities = new ArrayList<>();


    public BookEntity() {
    }

    public BookEntity(String title, String subtitle, String edition, Date printed, String isbn) {
        this.title = title;
        this.subtitle = subtitle;
        this.edition = edition;
        this.printed = printed;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getPrinted() {
        return printed;
    }

    public void setPrinted(Date printed) {
        this.printed = printed;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public UserEntity getBorrower() {
        return borrower;
    }

    public void setBorrower(UserEntity borrower) {
        this.borrower = borrower;
    }

    public void removeBorrower() {
        borrower.removeBook(this);
        borrower = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id.equals(that.id) && isbn.equals(that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
}
