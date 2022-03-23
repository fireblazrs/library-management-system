package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.iths.librarysystem.dto.Person;
import se.iths.librarysystem.entity.PersonEntity;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.PersonRepository;
import se.iths.librarysystem.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final String DEFAULT_ROLE = "ROLE_USER";

    public PersonService(PersonRepository repository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.personRepository = repository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public Person createPerson(Person person) {
        PersonEntity personEntity = modelMapper.map(person, PersonEntity.class);

        RoleEntity defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        personEntity.setRole(defaultRole);
        PersonEntity savedPersonEntity = personRepository.save(personEntity);

        return modelMapper.map(savedPersonEntity, Person.class);
    }

    public List<Person> getAllUsers() {
        Iterable<PersonEntity> personEntities = personRepository.findAll();
        List<Person> users = new ArrayList<>();
        personEntities.forEach(user -> users.add(modelMapper.map(user, Person.class)));
        return users;
    }

    public Person findById(Long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        return modelMapper.map(personEntity, Person.class);
    }


}
