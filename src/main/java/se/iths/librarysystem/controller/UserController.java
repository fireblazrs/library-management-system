package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.dto.User;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.service.UserService;
import se.iths.librarysystem.validatorservice.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserValidator validator;

    public UserController(UserService userService, ModelMapper modelMapper, UserValidator validator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        UserEntity UserEntity = modelMapper.map(user, UserEntity.class);
        UserEntity createdEntity = userService.createPerson(UserEntity);
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
        validator.validId(id);

        UserEntity userEntity = userService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        User user = modelMapper.map(userEntity, User.class);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        validator.validId(user.getId());
        validator.idExists(user.getId());

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity updatedUserEntity = userService.updatePerson(userEntity);
        User updatedUser = modelMapper.map(updatedUserEntity, User.class);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        validator.validId(id);
        validator.idExists(id);
        userService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}