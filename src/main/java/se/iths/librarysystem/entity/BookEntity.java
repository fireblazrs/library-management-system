package se.iths.librarysystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date printed;

    @NotBlank(message = "ISBN is a required field")
    private String isbn;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity borrower;

    @ManyToMany(mappedBy = "enrolledBooks", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<AuthorEntity> authors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BookFormatEntity> bookFormatEntities = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    private GenreEntity genreEntity;




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

    public BookEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public BookEntity setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getEdition() {
        return edition;
    }

    public BookEntity setEdition(String edition) {
        this.edition = edition;
        return this;
    }

    public Date getPrinted() {
        return printed;
    }

    public BookEntity setPrinted(Date printed) {
        this.printed = printed;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookEntity setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public UserEntity getBorrower() {
        return borrower;
    }

    public BookEntity setBorrower(UserEntity borrower) {
        this.borrower = borrower;
        return this;
    }

    public void removeBorrower() {
        borrower.removeBook(this);
        borrower = null;
    }

    public GenreEntity getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

    public Set<BookFormatEntity> getBookFormatEntities() {
        return bookFormatEntities;
    }

    public void addBookFormatEntity(BookFormatEntity bookFormatEntity) {
        bookFormatEntities.add(bookFormatEntity);
    }

    public void removeBookFormatEntity(BookFormatEntity bookFormatEntity) {
        bookFormatEntities.remove(bookFormatEntity);
    }

    public List<AuthorEntity> getAuthorEntities() {
        return Collections.unmodifiableList(authors);
    }

    public void addAuthorEntity(AuthorEntity authorEntity) {
        authors.add(authorEntity);
    }

    public void removeAuthorEntity(AuthorEntity authorEntity) {
        authors.remove(authorEntity);
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
