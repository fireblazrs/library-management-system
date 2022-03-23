package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    // CRUD


}
