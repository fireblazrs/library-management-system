package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.librarysystem.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByRole(String role);
}
