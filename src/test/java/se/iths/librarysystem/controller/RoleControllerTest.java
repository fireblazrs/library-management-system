package se.iths.librarysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.mocks.WithMockAdmin;
import se.iths.librarysystem.security.SecurityConfig;
import se.iths.librarysystem.service.RoleService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(RoleController.class)
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoleService roleService;


    @BeforeEach
    void setUp() {
    }

    @WithMockAdmin
    @Test
    void getAllRolesShouldReturnRoles() throws Exception {
        List<Role> roles = List.of(
                new Role("ROLE_USER").setId(1L),
                new Role("ROLE_ADMIN").setId(2L)
        );

        when(roleService.getAllRoles()).thenReturn(roles);

        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @WithMockAdmin
    @Test
    void getRoleByIdShouldReturnRole() throws Exception {
        Role role = new Role("ROLE_USER").setId(1L);

        when(roleService.getRoleById(anyLong())).thenReturn(role);

        mockMvc.perform(get("/api/roles/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("role").value("ROLE_USER"));

    }

    @WithMockUser
    @Test
    void whenRegularUserGetRoleByIdShouldReturnStatus403() throws Exception {
        mockMvc.perform(get("/api/roles/{id}", 1))
                .andExpect(status().isForbidden());
    }

    @WithAnonymousUser
    @Test
    void anonymousUserGetRoleByIdShouldReturnStatus401() throws Exception {
        mockMvc.perform(get("/api/roles/{id}", 1))
                .andExpect(status().isUnauthorized());
    }

    @WithMockAdmin
    @Test
    void createRoleShouldReturnStatus201AndRole() throws Exception {
        Role role = new Role("ROLE_USER").setId(1L);

        when(roleService.createRole(any(Role.class))).thenReturn(role);

        mockMvc.perform(post("/api/roles").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**/api/roles/1"))
                .andExpect(jsonPath("role").value("ROLE_USER"));
    }
}