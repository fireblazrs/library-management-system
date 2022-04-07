package se.iths.librarysystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(RoleService.class)
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private ModelMapper modelMapper;


    @BeforeEach
    void setUp() {
        RoleEntity roleEntity = new RoleEntity("ROLE_USER").setId(10L);
        Role role = new Role("ROLE_USER").setId(10L);

        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(roleEntity));
        when(modelMapper.map(any(Role.class), eq(RoleEntity.class))).thenReturn(roleEntity);
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(role);
    }

    @Test
    void findByIdShouldReturnOptionalRoleEntity() {
        var result = roleService.findById(10L);

        assertThat(result).isNotEmpty().contains(new RoleEntity().setId(10L).setRole("ROLE_USER"));
    }

    @Test
    void getAllRolesShouldReturnListOfRoles() {
        Iterable<RoleEntity> roleEntities = List.of(
                new RoleEntity("ROLE_USER").setId(10L),
                new RoleEntity("ROLE_ADMIN").setId(15L)
        );

        when(roleRepository.findAll()).thenReturn(roleEntities);

        var result = roleService.getAllRoles();

        assertThat(result).hasSize(2).contains(new Role("ROLE_USER").setId(10L));
    }

    @Test
    void getRoleByIdShouldReturnRole() {
        var result = roleService.getRoleById(10L);

        assertThat(result).isEqualTo(new Role("ROLE_USER").setId(10L));
    }

    @Test
    void createRole() {
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(new RoleEntity("ROLE_USER").setId(10L));

        var result = roleService.createRole(new Role("ROLE_USER"));

        assertThat(result).isEqualTo(new Role("ROLE_USER").setId(10L));
    }
}