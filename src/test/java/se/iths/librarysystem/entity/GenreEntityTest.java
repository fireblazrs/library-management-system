package se.iths.librarysystem.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenreEntityTest {

    @Test
    void getIdShouldReturnSetId(){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(3L);

        assertThat(genreEntity.getId()).isEqualTo(3L);
    }

    @Test
    void getGenreShouldReturnSetGenre(){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenreName("Mystery");

        assertThat(genreEntity.getGenreName()).isEqualTo("Mystery");
    }

    @Test
    void isNonFictionShouldReturnTrue(){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setFiction(true);

        assertTrue(genreEntity.isFiction());
    }

    @Test
    void verifyEqualsAndHashCode(){
        EqualsVerifier.forClass(GenreEntity.class).verify();
    }
}