package se.iths.librarysystem.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleEntityTest {

    @Test
    void getIdShouldReturnTheSetId() {
        RoleEntity role = new RoleEntity("ROLE_USER");
        role.setId(2L);

        Long result = role.getId();

        assertThat(result).isEqualTo(2L);
    }

    @Test
    void getRoleShouldReturnTheSetRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole("ROLE_USER");

        String result = roleEntity.getRole();

        assertThat(result).isEqualTo("ROLE_USER");
    }

    @Test
    void verifyEqualsAndHashCode() {
        RoleEntity user = new RoleEntity("ROLE_USER");
        RoleEntity admin = new RoleEntity("ROLE_ADMIN");
        PersonEntity person1 = new PersonEntity("Stacey", "Thomas", "19920523-1234", "stacey@themail.com", "0723456789");
        PersonEntity person2 = new PersonEntity("Per", "Olofsson", "19871205-1234", "per@olofsson.com", "0723456789");
        person1.setRole(user);
        user.addPerson(person1);
        person2.setRole(admin);
        admin.addPerson(person2);


        EqualsVerifier.forClass(RoleEntity.class)
                .withPrefabValues(PersonEntity.class, person1, person2)
                .withIgnoredFields("personList")
                .verify();
    }

}
