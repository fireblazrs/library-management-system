package se.iths.librarysystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookEntityTest {

    @Test
    void addUserShouldReturnAddedUser() {
        UserEntity user = new UserEntity();
        user.setId(21L);
        BookEntity book = new BookEntity();
        book.setBorrower(user);

        UserEntity result = book.getBorrower();

        assertThat(result).isEqualTo(user);
    }

}
