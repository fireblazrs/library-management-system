package se.iths.librarysystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genreName;
    private boolean fiction;


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
