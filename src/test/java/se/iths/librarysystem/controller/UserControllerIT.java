package se.iths.librarysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.librarysystem.dto.User;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.mocks.WithMockAdmin;
import se.iths.librarysystem.repository.RoleRepository;
import se.iths.librarysystem.repository.UserRepository;
import se.iths.librarysystem.security.SecurityConfig;
import se.iths.librarysystem.service.UserService;
import se.iths.librarysystem.validatorservice.UserValidator;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Import({UserService.class, SecurityConfig.class, UserValidator.class})
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @WithAnonymousUser
    @Test
    void whenAnonymousUserGetAllUserShouldReturnStatus401() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    void whenRegularUserGetAllUserShouldReturnStatus403() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isForbidden());
    }

    @WithMockAdmin
    @Test
    void getAllUserShouldReturnStatus200AndAllUsers() throws Exception {
        Iterable<UserEntity> userEntities = List.of(new UserEntity(), new UserEntity());
        User user = new User();

        when(userRepository.findAll()).thenReturn(userEntities);
        when(modelMapper.map(any(UserEntity.class), eq(User.class))).thenReturn(user);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
        verify(userRepository).findAll();
    }

    @WithMockAdmin
    @Test
    void deleteUserShouldReturnStatus204() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        mockMvc.perform(delete("/api/users/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @WithMockAdmin
    @Test
    void deleteNonExistentUserShouldReturnStatus404() throws Exception {
        when(userRepository.findById(1L)).thenThrow(new IdNotFoundException("user", 1L));
        mockMvc.perform(delete("/api/users/{id}", 1))
                .andExpect(status().isNotFound());
    }


    @WithMockAdmin
    @Test
    void createUserShouldReturnStatus201AndCreatedUser() throws Exception {
        User user = new User().setUsername("TomCat").setFirstname("Thomas").setLastname("Collins")
                .setSsn("880927-0000").setEmail("tomcat@gmail.com");
        UserEntity userEntity = new UserEntity().setUsername("TomCat").setFirstname("Thomas").setLastname("Collins")
                .setSsn("880927-0000").setEmail("tomcat@gmail.com").setPassword("1234").setId(10L);

        when(modelMapper.map(any(User.class), eq(UserEntity.class))).thenReturn(userEntity);
        when(roleRepository.findByRole(any(String.class))).thenReturn(new RoleEntity("ROLE_USER").setId(21L));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(modelMapper.map(any(UserEntity.class), eq(User.class))).thenReturn(user.setId(10L));

        mockMvc.perform(post("/api/users/new").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(10))
                .andExpect(redirectedUrlPattern("**/api/users/" + 10));
    }

    @WithMockAdmin
    @Test
    void updateUserShouldReturnStatus200AndUpdatedUser() throws Exception {

        String payload = """
                {
                  	"id": "10",
                  	"username": "TomCat",
                  	"password": "tomthecat#%22",
                  	"firstname": "Thomas",
                  	"lastname": "Collins",
                  	"ssn": "12345678",
                  	"email": "tomcat@gmail.com"
                  }
                """;

        UserEntity userEntity = new UserEntity().setUsername("TomCat").setFirstname("Thomas").setLastname("Collins")
                .setSsn("880927-0000").setEmail("tomcat@gmail.com").setPassword("tomthecat#%22").setId(10L);
        User user = new User().setUsername("TomCat").setFirstname("Thomas").setLastname("Collins")
                .setSsn("880927-0000").setEmail("tomcat@gmail.com").setId(10L);

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(any(User.class), eq(UserEntity.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(modelMapper.map(any(UserEntity.class), eq(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/users").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(10));
    }

    @WithMockUser
    @Test
    void getUserByIdShouldReturnExpectedUser() throws Exception {
        Optional<UserEntity> userEntity = Optional.of(new UserEntity().setId(25L));
        User user = new User().setId(25L);

        when(userRepository.findById(any(Long.class))).thenReturn(userEntity);
        when(modelMapper.map(any(UserEntity.class), eq(User.class))).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", 25))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(25));
    }

}