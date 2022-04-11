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
    void verifyEqualsAndHashCode() {
        GenreEntity genre = new GenreEntity("Mystery", true);
        BookEntity book1 = new BookEntity().setId(1L).setTitle("Secrets in the Study").setIsbn("9032344235629");
        BookEntity book2 = new BookEntity().setId(2L).setTitle("Murder Alley").setIsbn("9432100987234");
        genre.addBook(book1);
        genre.addBook(book2);

        EqualsVerifier.forClass(GenreEntity.class)
            .withPrefabValues(BookEntity.class, book1, book2)
            .withIgnoredFields("bookEntities")
            .verify();
    }
}