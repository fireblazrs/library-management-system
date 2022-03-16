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
        String role = "ROLE_USER";
        roleEntity.setRole(role);

        String result = roleEntity.getRole();

        assertThat(result).isEqualTo(role);
    }

    @Test
    void verifyEqualsAndHashCode() {
        EqualsVerifier.forClass(RoleEntity.class).verify();
    }

}