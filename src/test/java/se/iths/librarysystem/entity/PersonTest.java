package se.iths.librarysystem.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    @Test
    void getIdShouldReturnSetId() {
        Person person = new Person("Stacey", "Thomas", "19920523-1234", "stacey@themail.com", "0723456789");
        person.setId(21L);

        Long result = person.getId();

        assertThat(result).isEqualTo(21L);
    }

    @Test
    void getEmailShouldReturnSetEmail() {
        Person person = new Person("Stacey", "Thomas", "19920523-1234", "stacey@themail.com");
        person.setEmail("stacey@themail.com");

        String result = person.getEmail();

        assertThat(result).isEqualTo("stacey@themail.com");
    }

    @Test
    void getPhoneNumberShouldReturnSetPhoneNumber() {
        Person person = new Person();
        person.setPhoneNumber("0723456789");

        String result = person.getPhoneNumber();

        assertThat(result).isEqualTo("0723456789");
    }

    @Test
    void getFirstNameShouldReturnSetFirstName() {
        Person person = new Person();
        person.setFirstname("Per");

        String result = person.getFirstname();

        assertThat(result).isEqualTo("Per");
    }

    @Test
    void getLastNameShouldReturnLastName() {
        Person person = new Person();
        person.setLastname("Andersson");

        String result = person.getLastname();

        assertThat(result).isEqualTo("Andersson");
    }

    @Test
    void getDobShouldReturnSetDob() {
        Person person = new Person();
        person.setSsn("1978-12-21");

        String result = person.getSsn();

        assertThat(result).isEqualTo("1978-12-21");
    }

    @Test
    void getAddressShouldReturnSetAddress() {
        Person person = new Person();
        person.setAddress("15 Meadows Lane, Wessex, England");

        String result = person.getAddress();

        assertThat(result).isEqualTo("15 Meadows Lane, Wessex, England");
    }

}