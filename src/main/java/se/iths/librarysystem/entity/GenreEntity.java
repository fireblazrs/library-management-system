package se.iths.librarysystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genreName;
    private boolean fiction;

    @Transient
    @OneToMany(mappedBy = "genreEntity")
    private List<BookEntity> bookEntities = new ArrayList<>();

    public GenreEntity(){
    }


    public GenreEntity(String genreName, boolean fiction, List<BookEntity> bookEntities) {
        this.genreName = genreName;
        this.fiction = fiction;
        this.bookEntities = bookEntities;
    }


    public Long getId() {
        return id;
    }

    public GenreEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public List<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(List<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
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
        if (!(o instanceof GenreEntity)) return false;
        GenreEntity genre = (GenreEntity) o;
        return fiction == genre.fiction && Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreName, fiction);
    }
}
