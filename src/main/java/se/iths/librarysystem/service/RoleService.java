package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import se.iths.librarysystem.entity.RoleEntity;
import se.iths.librarysystem.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
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

    public List<RoleEntity> getAllRoles() {
        Iterable<RoleEntity> roleEntities = roleRepository.findAll();
        List<RoleEntity> roleEntityList = new ArrayList<>();
        roleEntities.forEach(roleEntityList::add);
        return roleEntityList;
    }

    public Optional<RoleEntity> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public RoleEntity createRole(RoleEntity newRoleEntity) {
        return roleRepository.save(newRoleEntity);
    }

}
