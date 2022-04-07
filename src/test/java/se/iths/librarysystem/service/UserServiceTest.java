package se.iths.librarysystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.librarysystem.dto.*;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.RoleRepository;
import se.iths.librarysystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(UserService.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private ModelMapper modelMapper;


    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity().setId(46L);
        User user = new User().setId(46L);

        when(modelMapper.map(any(User.class), eq(UserEntity.class))).thenReturn(userEntity);
        when(modelMapper.map(any(UserEntity.class), eq(User.class))).thenReturn(user);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
    }

    @Test
    void createUserShouldReturnNewUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("#@&%ja-8jnn-&%gbk#");
        when(roleRepository.findByRole(anyString())).thenReturn(new RoleEntity("ROLE_USER").setId(15L));

        User result = userService.createUser(new NewUser());

        assertThat(result).isEqualTo(new User().setId(46L));
    }

    @Test
    void getAllUsersShouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new UserEntity()));

        var result = userService.getAllUsers();

        assertThat(result).hasSize(1).contains(new User().setId(46L));
    }

    @Test
    void findUserEntityByIdShouldReturnUserEntityOptional() {
        var result = userService.findUserEntityById(46L);

        assertThat(result).isNotEmpty().contains(new UserEntity().setId(46L));
    }

    @Test
    void findUserByIdShouldReturnUser() {
        var result = userService.findUserById(46L);

        assertThat(result).isEqualTo(new User().setId(46L));
    }

    @Test
    void findUserByIdShouldThrowError() {
        when(userRepository.findById(anyLong())).thenThrow(new IdNotFoundException("user", 46L));

        assertThatThrownBy(() -> userService.findUserById(46L)).hasMessage("user with Id 46 not found.");
    }

    @Test
    void updateUserShouldReturnUser() {
        var result = userService.updateUser(new User());

        assertThat(result).isEqualTo(new User().setId(46L));
    }

    @Test
    void addRoleToUserShouldReturnUserWithRoleDTO() {
        UserWithRole userWithRole = new UserWithRole().setId(46L).addRole(new Role("ROLE_USER").setId(2L));

        when(roleRepository.findById(anyLong())).thenReturn(Optional.ofNullable(new RoleEntity("ROLE_USER").setId(2L)));
        when(modelMapper.map(any(UserEntity.class), eq(UserWithRole.class))).thenReturn(userWithRole);

        var result = userService.addRoleToUser(46L, 2L);

        assertThat(result).isEqualTo(new UserWithRole().setId(46L));
        assertThat(result.getRoles()).contains(new Role("ROLE_USER").setId(2L));
    }

    @Test
    void getUserRolesShouldReturnListOfAUsersRoles() {
        UserEntity userEntity = new UserEntity().setId(46L).addRole(new RoleEntity("ROLE_USER"));

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(any(RoleEntity.class), eq(Role.class))).thenReturn(new Role("ROLE_USER").setId(2L));

        var result = userService.getUserRoles(46L);

        assertThat(result).hasSize(1).contains(new Role("ROLE_USER").setId(2L));
    }

    @Test
    void getUserBooksShouldReturnListOfUsersBooks() {
        UserEntity userEntity = new UserEntity().setId(46L)
                .addBooks(Set.of(
                        new BookEntity().setId(78L).setIsbn("9000929"),
                        new BookEntity().setId(89L).setIsbn("9000987"))
                );
        Book book = new Book().setId(78L).setIsbn("90009");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(modelMapper.map(any(BookEntity.class), eq(Book.class))).thenReturn(book);

        var result = userService.getUserBooks(46L);

        assertThat(result).hasSize(2).contains(new Book().setId(78L).setIsbn("90009"));
    }
}