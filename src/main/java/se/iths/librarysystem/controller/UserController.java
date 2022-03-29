package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.dto.User;
import se.iths.librarysystem.dto.UserWithRole;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.service.UserService;
import se.iths.librarysystem.validatorservice.RoleValidator;
import se.iths.librarysystem.validatorservice.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;
    private final RoleValidator roleValidator;

    public UserController(UserService userService, ModelMapper modelMapper, UserValidator userValidator,
                          RoleValidator roleValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userValidator = userValidator;
        this.roleValidator = roleValidator;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity createdEntity = userService.createPerson(userEntity);
        User createdUser = modelMapper.map(createdEntity, User.class);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        Iterable<UserEntity> userEntities = userService.getAllPersons();
        List<User> users = new ArrayList<>();
        userEntities.forEach(userEntity -> users.add(modelMapper.map(userEntity, User.class)));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        userValidator.validId(id);

        UserEntity userEntity = userService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        User user = modelMapper.map(userEntity, User.class);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        userValidator.validId(user.getId());
        userValidator.idExists(user.getId());

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity updatedUserEntity = userService.updatePerson(userEntity);
        User updatedUser = modelMapper.map(updatedUserEntity, User.class);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userValidator.validId(id);
        UserEntity userEntity = userService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        userEntity.removeRole();
        userService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}/role")
    public ResponseEntity<Role> getUserRole(@PathVariable Long id) {
        userValidator.validId(id);
        UserEntity userEntity = userService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        RoleEntity roleEntity = userEntity.getRole();
        Role role = modelMapper.map(roleEntity, Role.class);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PatchMapping("{userId}/role/{roleId}")
    public ResponseEntity<UserWithRole> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userValidator.validId(userId);
        roleValidator.validId(roleId);

        UserEntity userEntity = userService.addRoleToUser(userId, roleId);
        UserWithRole user = modelMapper.map(userEntity, UserWithRole.class);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
