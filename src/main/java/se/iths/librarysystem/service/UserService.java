package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.RoleRepository;
import se.iths.librarysystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final String DEFAULT_ROLE = "ROLE_USER";

    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
    }

    public UserEntity createPerson(UserEntity userEntity) {
        RoleEntity defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        userEntity.setRole(defaultRole);
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAllPersons() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntities.forEach(userEntityList::add);
        return userEntityList;
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

    @Transactional
    public UserEntity addRoleToUser(Long userId, Long roleId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException("user", userId));
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new IdNotFoundException("role", roleId));
        user.setRole(role);
        return user;
    }
}
