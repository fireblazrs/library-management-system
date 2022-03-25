package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository personRepository;

    public UserService(UserRepository personRepository) {
        this.personRepository = personRepository;
    }

    public UserEntity createPerson(UserEntity userEntity) {
        return personRepository.save(userEntity);
    }

    public Iterable<UserEntity> findAllPersons() {
        return personRepository.findAll();
    }
}
