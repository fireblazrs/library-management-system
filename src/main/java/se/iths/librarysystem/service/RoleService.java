package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {
    
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<RoleEntity> findById(Long id) {
        return roleRepository.findById(id);
    }

    public Iterable<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public RoleEntity createRole(RoleEntity newRoleEntity) {
        return roleRepository.save(newRoleEntity);
    }

}
