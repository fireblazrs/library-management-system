package se.iths.librarysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.repository.RoleRepository;
import se.iths.librarysystem.security.SecurityConfig;
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

@Import({RoleService.class, SecurityConfig.class})
@WebMvcTest(RoleController.class)
@AutoConfigureMockMvc
class RoleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("An unauthenticated user should return 401: UNAUTHORIZED")
    void anUnauthenticatedUserShouldReturnStatus401() throws Exception {
        when(roleRepository.findAll()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    @DisplayName("An unauthorized user should return Status 403: FORBIDDEN")
    void anUnauthorizedUserShouldReturnStatus403() throws Exception {
        when(roleRepository.findAll()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles={"ADMIN"})
    @Test
    @DisplayName("Get all users should return 0 roles")
    void getAllRolesReturnsEmptyList() throws Exception {
        when(roleRepository.findAll()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/roles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @WithMockUser(roles={"ADMIN"})
    @Test
    @DisplayName("Get all users should return 2 roles")
    void getAllRoles() throws Exception {
        Iterable<RoleEntity> roleEntities = List.of(new RoleEntity("ROLE_ADMIN"), new RoleEntity("ROLE_USER"));

        when(roleRepository.findAll()).thenReturn(roleEntities);
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(new Role("ROLE_ADMIN"));

        mockMvc.perform(get("/api/roles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].role", is("ROLE_ADMIN")));
    }

    @WithMockUser(roles={"ADMIN"})
    @Test
    @DisplayName("Get role by Id should return status OK and expected role")
    void getRoleByIdShouldReturnStatus200() throws Exception {
        Long id = 1L;
        RoleEntity roleEntity = new RoleEntity("ROLE_ADMIN");
        roleEntity.setId(id);
        Optional<RoleEntity> roleOptional = Optional.of(roleEntity);
        Role role = new Role("ROLE_ADMIN");

        when(roleRepository.findById(any(Long.class))).thenReturn(roleOptional);
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(role);

        mockMvc.perform(get("/api/roles/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role", is("ROLE_ADMIN")));
    }

    @WithMockUser(roles={"ADMIN"})
    @Test
    @DisplayName("Get role by Id should return status NOT_FOUND, an error message and path")
    void getRoleByIdShouldReturnError404() throws Exception {
        Long id = 1L;

        when(roleRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/roles/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("path").value("/api/roles/" + id))
                .andExpect(jsonPath("message").value("role with Id " + id + " not found."));
    }

    @WithMockUser(roles={"ADMIN"})
    @Test
    @DisplayName("Create valid role should return status 'CREATED' AND role = ROLE_ADMIN")
    void createRoleShouldReturnStatus201() throws Exception {
        RoleEntity roleEntity = new RoleEntity("ROLE_ADMIN");

        RoleEntity savedEntity = new RoleEntity("ROLE_ADMIN");
        Long id = 1L;
        savedEntity.setId(id);

        Role role = new Role("ROLE_ADMIN");
        role.setId(id);

        when(modelMapper.map(any(Role.class), eq(RoleEntity.class))).thenReturn(roleEntity);
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(savedEntity);
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(role);

        mockMvc.perform(post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("role").value("ROLE_ADMIN"));
    }

}