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
        UserEntity user1 = new UserEntity("Stacey", "Thomas", "19920523-1234", "stacey@themail.com", "0723456789");
        UserEntity user2 = new UserEntity("Per", "Olofsson", "19871205-1234", "per@olofsson.com", "0723456789");
        user1.setRole(user);
        user.addUser(user1);
        user2.setRole(admin);
        admin.addUser(user2);


        EqualsVerifier.forClass(RoleEntity.class)
                .withPrefabValues(UserEntity.class, user1, user2)
                .withIgnoredFields("users")
                .verify();
    }

}
