package se.iths.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class Book {

    private Long id;
    private String title;
    private String subtitle;
    private String edition;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date printed;
    private String isbn;

    public Book() {
    }

    public Book(String title, String subtitle, String edition, Date printed, String isbn) {
        this.title = title;
        this.subtitle = subtitle;
        this.edition = edition;
        this.printed = printed;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Book setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getEdition() {
        return edition;
    }

    public Book setEdition(String edition) {
        this.edition = edition;
        return this;
    }

    public Date getPrinted() {
        return printed;
    }

    public Book setPrinted(Date printed) {
        this.printed = printed;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title)
               && Objects.equals(subtitle, book.subtitle) && Objects.equals(edition, book.edition)
               && Objects.equals(printed, book.printed) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subtitle, edition, printed, isbn);
    }
}
