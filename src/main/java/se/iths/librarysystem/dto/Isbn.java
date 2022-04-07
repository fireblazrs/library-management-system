package se.iths.librarysystem.dto;

public class Isbn {

    private String isbn;

    public Isbn() {
    }

    public Isbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
