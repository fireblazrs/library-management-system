package se.iths.librarysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.service.RoleService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RoleController.class)
@AutoConfigureMockMvc
class RoleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Get all users should return 0 roles")
    void getAllRolesReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/roles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Get all users should return 2 roles")
    void getAllRoles() throws Exception {
        List<Role> roles = List.of(new Role("ROLE_AUTHOR"), new Role("ROLE_USER"));
        Iterable<RoleEntity> roleEntities = List.of(new RoleEntity("ROLE_AUTHOR"), new RoleEntity("ROLE_USER"));

        BDDMockito.given(roleService.getAllRoles()).willReturn((roleEntities));
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(new Role("ROLE_AUTHOR"));

        mockMvc.perform(get("/roles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].role", is(roles.get(0).getRole())));
    }

    @Test
    @DisplayName("Get role by Id should return status OK and expected role")
    void getRoleByIdShouldReturnStatus200() throws Exception {
        Long id = 1L;
        RoleEntity roleEntity = new RoleEntity("ROLE_ADMIN");
        roleEntity.setId(id);

        when(roleService.getRoleById(any(Long.class))).thenReturn(Optional.of(roleEntity));
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(new Role("ROLE_ADMIN"));

        mockMvc.perform(get("/roles/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role", is("ROLE_ADMIN")));
    }

    @Test
    @DisplayName("Get role by Id should return status NOT_FOUND, an error message and path")
    void getRoleByIdShouldReturnError404() throws Exception {
        Long id = 1L;

        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(new Role("ROLE_ADMIN"));

        mockMvc.perform(get("/roles/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("path").value("/roles/" + id))
                .andExpect(jsonPath("message").value("role with Id " + id + " not found."));
    }

    @Test
    @DisplayName("Create valid role should return status 'CREATED' AND role = ROLE_AUTHOR")
    void createRoleShouldReturnStatus201() throws Exception {
        RoleEntity roleEntity = new RoleEntity("ROLE_AUTHOR");
        Role role = new Role("ROLE_AUTHOR");
        roleEntity.setId(20L);

        when(modelMapper.map(any(Role.class), eq(RoleEntity.class))).thenReturn(roleEntity);
        when(roleService.createRole(any(RoleEntity.class))).thenReturn(roleEntity);
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(role);

        mockMvc.perform(post("/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.role").value("ROLE_AUTHOR"));
    }

}