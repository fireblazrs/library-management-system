package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService personService;

    public UserController(UserService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<UserEntity> createItem(@RequestBody UserEntity personEntity){
        var createdPerson = personService.createPerson(personEntity);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<UserEntity>> findAllPersons() {
        Iterable<UserEntity> allPersons = personService.findAllPersons();
        return new ResponseEntity<>(allPersons, HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity<String> testEndPoint(){
        return new ResponseEntity<>("Hello, World", HttpStatus.OK);
    }
}
