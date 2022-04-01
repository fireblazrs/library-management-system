package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.librarysystem.dto.*;
import se.iths.librarysystem.entity.BookEntity;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.entity.TaskEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.ValueNotFoundException;
import se.iths.librarysystem.queue.QueueHandler;
import se.iths.librarysystem.service.BookService;
import se.iths.librarysystem.service.TaskService;
import se.iths.librarysystem.service.UserService;
import se.iths.librarysystem.validatorservice.BookValidator;
import se.iths.librarysystem.validatorservice.UserValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;
    private final BookValidator bookValidator;
    private final BookService bookService;
    private final TaskService taskService;
    private final QueueHandler queueHandler;

    public UserController(UserService userService, ModelMapper modelMapper, UserValidator userValidator,
                          BookValidator bookValidator, BookService bookService, TaskService taskService, QueueHandler queueHandler) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userValidator = userValidator;
        this.bookValidator = bookValidator;
        this.bookService = bookService;
        this.taskService = taskService;
        this.queueHandler = queueHandler;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity createdEntity = userService.createPerson(userEntity);
        User createdUser = modelMapper.map(createdEntity, User.class);
        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build().toString() + createdEntity.getId()))
                .body(createdUser);
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
        RoleEntity roleEntity = Optional.ofNullable(userEntity.getRole())
            .orElseThrow(() -> new ValueNotFoundException("User Id "+ id + " does not have a role.", "/users/" + id + "/role"));
        Role role = modelMapper.map(roleEntity, Role.class);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("{id}/books")
    public ResponseEntity<List<Book>> getAUsersBooks(@PathVariable Long id) {
        userValidator.validId(id);
        UserEntity userEntity = userService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        List<Book> books = userEntity.getBooks().stream()
                .map(book -> modelMapper.map(book, Book.class))
                .toList();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("{userId}/books/{bookId}")
    public ResponseEntity<Void> removeBookUserConnection(@PathVariable Long userId, @PathVariable Long bookId) {
        userValidator.validId(userId);
        bookValidator.validId(bookId);
        UserEntity user = userService.findById(userId).orElseThrow(() -> new IdNotFoundException("user", userId));
        BookEntity book = bookService.findById(bookId).orElseThrow(() -> new IdNotFoundException("book", bookId));
        bookValidator.hasUser(book, user);
        book.removeBorrower();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/books")
    public ResponseEntity<Task> addBookToUser(@PathVariable Long id, @RequestBody Isbn isbn) {
        userValidator.validId(id);
        userValidator.idExists(id);
        bookValidator.isbnExists(isbn);

        TaskEntity loanTask = new TaskEntity(isbn.getIsbn(), id);
        TaskEntity savedTask = taskService.createTask(loanTask);
        Task task = queueHandler.sendToQueue(savedTask);

        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

}
