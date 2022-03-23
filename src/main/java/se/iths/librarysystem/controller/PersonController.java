package se.iths.librarysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.dto.Person;
import se.iths.librarysystem.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("users")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<Person> createUser(@RequestBody Person person) {
        Person newPerson = personService.createPerson(person);

        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllUsers() {
        List<Person> users = personService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> findUserById(@PathVariable Long id) {
        Person person = personService.findById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
