package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.repository.UserRepository;
import se.iths.librarysystem.repository.RoleRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final String DEFAULT_ROLE = "ROLE_USER";

    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
    }

    public UserEntity createPerson(UserEntity userEntity) {
        RoleEntity defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        userEntity.setRole(defaultRole);
        return userRepository.save(userEntity);
    }

    public Iterable<UserEntity> getAllPersons() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity updatePerson(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void deletePerson(Long id) {
        userRepository.deleteById(id);
    }

}
