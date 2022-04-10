package se.iths.librarysystem.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.dto.UserWithRole;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.mocks.WithMockAdmin;
import se.iths.librarysystem.repository.RoleRepository;
import se.iths.librarysystem.repository.UserRepository;
import se.iths.librarysystem.security.SecurityConfig;
import se.iths.librarysystem.service.RoleService;
import se.iths.librarysystem.service.UserService;
import se.iths.librarysystem.validatorservice.RoleValidator;
import se.iths.librarysystem.validatorservice.UserValidator;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import({UserService.class, SecurityConfig.class, UserValidator.class, RoleValidator.class, RoleService.class})
@WebMvcTest(UserRoleController.class)
@AutoConfigureMockMvc
class UserRoleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private ModelMapper modelMapper;


    @WithMockAdmin
    @Test
    void getUserRoleShouldReturnRole() throws Exception {
        List<RoleEntity> roleEntities = List.of(new RoleEntity("ROLE_USER"), new RoleEntity("ROLE_ADMIN"));
        UserEntity userEntity = new UserEntity().setId(15L);
        roleEntities.forEach(userEntity::addRole);

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(new Role("ROLE_USER"));

        mockMvc.perform(get("/api/users/{id}/role", 15))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].role").value("ROLE_USER"));
    }


    @WithMockAdmin
    @Test
    void updateUserRoleShouldReturnUpdatedUserWithRole() throws Exception {
        UserEntity userEntity = new UserEntity().setId(35L);
        RoleEntity roleEntity = new RoleEntity("ROLE_ADMIN").setId(5L);
        Role role = new Role("ROLE_ADMIN").setId(15L);
        UserWithRole user = new UserWithRole().addRole(role).setId(35L);

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userEntity));
        when(roleRepository.findById(any(Long.class))).thenReturn(Optional.of(roleEntity));
        when(modelMapper.map(any(UserEntity.class), eq(UserWithRole.class))).thenReturn(user);

        mockMvc.perform(patch("/api/users/{userId}/role/{roleId}", 35, 5))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(35)))
                .andExpect(jsonPath("roles.length()", is(1)))
                .andExpect(jsonPath("roles[0].role").value("ROLE_ADMIN"))
                .andExpect(jsonPath("roles[0].id").value(15));
        verify(userRepository).save(any(UserEntity.class));
    }

}
