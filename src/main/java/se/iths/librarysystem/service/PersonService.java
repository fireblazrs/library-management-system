package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.PersonEntity;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.repository.PersonRepository;
import se.iths.librarysystem.repository.RoleRepository;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final String DEFAULT_ROLE = "ROLE_USER";

    public PersonService(PersonRepository repository, RoleRepository roleRepository) {
        this.personRepository = repository;
        this.roleRepository = roleRepository;
    }

    public PersonEntity createPerson(PersonEntity personEntity) {
        RoleEntity defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        personEntity.setRole(defaultRole);
        return personRepository.save(personEntity);
    }

    public Iterable<PersonEntity> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<PersonEntity> findById(Long id) {
        return personRepository.findById(id);
    }

    public PersonEntity updatePerson(PersonEntity personEntity) {
        return personRepository.save(personEntity);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
