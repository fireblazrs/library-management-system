package se.iths.librarysystem.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {

    @Test
    void getIdShouldReturnSetId() {
        UserEntity userEntity = new UserEntity("Stacey", "Thomas", "19920523-1234", "stacey@themail.com", "0723456789");
        userEntity.setId(21L);

        Long result = userEntity.getId();

        assertThat(result).isEqualTo(21L);
    }

    @Test
    void getEmailShouldReturnSetEmail() {
        UserEntity userEntity = new UserEntity("Stacey", "Thomas", "19920523-1234", "stacey@themail.com");
        userEntity.setEmail("stacey@themail.com");

        String result = userEntity.getEmail();

        assertThat(result).isEqualTo("stacey@themail.com");
    }

    @Test
    void getPhoneNumberShouldReturnSetPhoneNumber() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber("0723456789");

        String result = userEntity.getPhoneNumber();

        assertThat(result).isEqualTo("0723456789");
    }

    @Test
    void getFirstNameShouldReturnSetFirstName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname("Per");

        String result = userEntity.getFirstname();

        assertThat(result).isEqualTo("Per");
    }

    @Test
    void getLastNameShouldReturnLastName() {
        UserEntity userEntity = new UserEntity();
        userEntity.setLastname("Andersson");

        String result = userEntity.getLastname();

        assertThat(result).isEqualTo("Andersson");
    }

    @Test
    void getDobShouldReturnSetDob() {
        UserEntity userEntity = new UserEntity();
        userEntity.setSsn("1978-12-21");

        String result = userEntity.getSsn();

        assertThat(result).isEqualTo("1978-12-21");
    }

    @Test
    void getAddressShouldReturnSetAddress() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("15 Meadows Lane, Wessex, England");

        String result = userEntity.getAddress();

        assertThat(result).isEqualTo("15 Meadows Lane, Wessex, England");
    }

    @Test
    void getRoomShouldReturnSetRoom() {
        UserEntity userEntity = new UserEntity();
        RoomEntity roomEntity = Mockito.mock(RoomEntity.class);

        userEntity.setRoom(roomEntity);

        assertEquals(userEntity.getRoom(), roomEntity);
    }

    @Test
    void getBooksShouldReturnAddedBooks() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(21L);
        BookEntity geography = new BookEntity();
        BookEntity science = new BookEntity();
        geography.setId(1L);
        science.setId(2L);
        userEntity.addBook(geography);
        userEntity.addBook(science);

        var result = userEntity.getBooks();

        assertThat(result).hasSize(2).contains(geography, science);
    }

    @Test
    void removeBooksShouldRemoveBooksFromUser() {
        UserEntity user = new UserEntity();
        BookEntity book = new BookEntity();
        user.setId(21L);
        book.setId(22L);
        user.addBook(book);
        book.setBorrower(user);

        book.removeBorrower();

        UserEntity result = book.getBorrower();
        var userBooks = user.getBooks();

        assertThat(result).isNull();
        assertThat(userBooks).isEmpty();
    }

}