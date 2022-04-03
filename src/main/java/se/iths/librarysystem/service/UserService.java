package se.iths.librarysystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.librarysystem.dto.Book;
import se.iths.librarysystem.dto.Role;
import se.iths.librarysystem.dto.User;
import se.iths.librarysystem.dto.UserWithRole;
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

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository repository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public User createUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        RoleEntity defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        userEntity.addRole(defaultRole);

        UserEntity savedEntity = userRepository.save(userEntity);
        return modelMapper.map(savedEntity, User.class);
    }

    public List<User> getAllUsers() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        List<User> users = new ArrayList<>();
        userEntities.forEach(user -> users.add(modelMapper.map(user, User.class)));
        return users;
    }

    public Optional<UserEntity> findUserEntityById(Long id) {
        return userRepository.findById(id);
    }

    public User findUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        return modelMapper.map(user, User.class);
    }

    public User updateUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity updatedUser = userRepository.save(userEntity);
        return modelMapper.map(updatedUser, User.class);
    }

    public void updateUserEntity(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Transactional
    public void deletePerson(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        userEntity.getRoles().forEach(userEntity::removeRole);
        userRepository.deleteById(id);
    }

    @Transactional
    public UserWithRole addRoleToUser(Long userId, Long roleId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException("user", userId));
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new IdNotFoundException("role", roleId));
        user.addRole(role);
        return modelMapper.map(user, UserWithRole.class);
    }

    public List<Role> getUserRoles(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        return userEntity.getRoles().stream()
                .map(role -> modelMapper.map(role, Role.class))
                .toList();
    }

    public List<Book> getUserBooks(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
        return userEntity.getBooks().stream()
                .map(book -> modelMapper.map(book, Book.class))
                .toList();
    }
}
