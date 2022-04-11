package se.iths.librarysystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genreName;
    private boolean fiction;

    @OneToMany(mappedBy = "genreEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<BookEntity> bookEntities = new ArrayList<>();

    public GenreEntity(){
    }


    public GenreEntity(String genreName, boolean fiction) {
        this.genreName = genreName;
        this.fiction = fiction;
    }


    public Long getId() {
        return id;
    }

    public GenreEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public List<BookEntity> getBooks() {
        return Collections.unmodifiableList(bookEntities);
    }

    public GenreEntity addBooks(List<BookEntity> books) {
        this.bookEntities.addAll(books);
        return this;
    }

    public void addBook(BookEntity book) {
        bookEntities.add(book);
    }

    public void removeBook(BookEntity book) {
        bookEntities.remove(book);
    }

    public String getGenreName() {
        return genreName;
    }

    public GenreEntity setGenreName(String genre) {
        this.genreName = genre;
        return this;
    }

    public boolean isFiction() {
        return fiction;
    }

    public GenreEntity setFiction(boolean nonFiction) {
        this.fiction = nonFiction;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreEntity genre)) return false;
        return fiction == genre.fiction && Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreName, fiction);
    }
}
