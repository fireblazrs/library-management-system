package se.iths.librarysystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genreName;
    private boolean nonFiction;


    public Genre(){
    }

    public Genre(String genreName, boolean nonFiction) {
        this.genreName = genreName;
        this.nonFiction = nonFiction;
    }

    public Long getId() {
        return id;
    }

    public Genre setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGenreName() {
        return genreName;
    }

    public Genre setGenreName(String genre) {
        this.genreName = genre;
        return this;
    }

    public boolean isNonFiction() {
        return nonFiction;
    }

    public Genre setNonFiction(boolean nonFiction) {
        this.nonFiction = nonFiction;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return nonFiction == genre.nonFiction &&
            Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreName, nonFiction);
    }
}
