package se.iths.librarysystem.dto;

import java.util.Date;

public class Book {

    private Long id;
    private String title;
    private String subtitle;
    private String edition;
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

}
