package se.iths.librarysystem.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    @Test
    void getIdShouldReturnSetId(){
        Genre genre = new Genre();
        genre.setId(3L);

        assertThat(genre.getId()).isEqualTo(3L);
    }

    @Test
    void getGenreShouldReturnSetGenre(){
        Genre genre = new Genre();
        genre.setGenreName("Mystery");

        assertThat(genre.getGenreName()).isEqualTo("Mystery");
    }

    @Test
    void isNonFictionShouldReturnTrue(){
        Genre genre = new Genre();
        genre.setNonFiction(true);

        assertTrue(genre.isNonFiction());
    }

    @Test
    void verifyEqualsAndHashCode(){
        EqualsVerifier.forClass(Genre.class).verify();
    }
}