package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.dto.Person;
import se.iths.librarysystem.entity.PersonEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.service.PersonService;
import se.iths.librarysystem.validatorservice.PersonValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class PersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;
    private final PersonValidator validator;

    public PersonController(PersonService personService, ModelMapper modelMapper, PersonValidator validator) {
        this.personService = personService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @PostMapping()
    public ResponseEntity<Person> createUser(@RequestBody Person person) {
        PersonEntity personEntity = modelMapper.map(person, PersonEntity.class);
        // todo: validate PersonEntity OR throw Invalid details

        PersonEntity createdEntity = personService.createPerson(personEntity);
        Person createdPerson = modelMapper.map(createdEntity, Person.class);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllUsers() {
        Iterable<PersonEntity> userEntities = personService.getAllPersons();
        List<Person> users = new ArrayList<>();
        userEntities.forEach(userEntity -> users.add(modelMapper.map(userEntity, Person.class)));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> findUserById(@PathVariable Long id) {
        validator.validId(id);

        PersonEntity personEntity = personService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        Person person = modelMapper.map(personEntity, Person.class);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Person> updateUser(@RequestBody Person person) {
        validator.validId(person.getId());
        validator.idExists(person.getId());

        PersonEntity personEntity = modelMapper.map(person, PersonEntity.class);
        PersonEntity updatedPersonEntity = personService.updatePerson(personEntity);
        Person updatedPerson = modelMapper.map(updatedPersonEntity, Person.class);

        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        validator.validId(id);
        validator.idExists(id);
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
