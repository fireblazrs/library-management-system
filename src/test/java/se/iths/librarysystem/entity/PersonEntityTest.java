package se.iths.librarysystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonEntityTest {

    @Test
    void getIdShouldReturnSetId() {
        PersonEntity personEntity = new PersonEntity("Stacey", "Thomas", "19920523-1234", "stacey@themail.com", "0723456789");
        personEntity.setId(21L);

        Long result = personEntity.getId();

        assertThat(result).isEqualTo(21L);
    }

    @Test
    void getEmailShouldReturnSetEmail() {
        PersonEntity personEntity = new PersonEntity("Stacey", "Thomas", "19920523-1234", "stacey@themail.com");
        personEntity.setEmail("stacey@themail.com");

        String result = personEntity.getEmail();

        assertThat(result).isEqualTo("stacey@themail.com");
    }

    @Test
    void getPhoneNumberShouldReturnSetPhoneNumber() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPhoneNumber("0723456789");

        String result = personEntity.getPhoneNumber();

        assertThat(result).isEqualTo("0723456789");
    }

    @Test
    void getFirstNameShouldReturnSetFirstName() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname("Per");

        String result = personEntity.getFirstname();

        assertThat(result).isEqualTo("Per");
    }

    @Test
    void getLastNameShouldReturnLastName() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setLastname("Andersson");

        String result = personEntity.getLastname();

        assertThat(result).isEqualTo("Andersson");
    }

    @Test
    void getDobShouldReturnSetDob() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setSsn("1978-12-21");

        String result = personEntity.getSsn();

        assertThat(result).isEqualTo("1978-12-21");
    }

    @Test
    void getAddressShouldReturnSetAddress() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setAddress("15 Meadows Lane, Wessex, England");

        String result = personEntity.getAddress();

        assertThat(result).isEqualTo("15 Meadows Lane, Wessex, England");
    }

}