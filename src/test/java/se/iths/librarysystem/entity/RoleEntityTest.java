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
    void getRoleShouldReturnTheSetRol() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole("ROLE_USER");

        String result = roleEntity.getRole();

        assertThat(result).isEqualTo("ROLE_USER");
    }

    @Test
    void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(RoleEntity.class).verify();
    }

}