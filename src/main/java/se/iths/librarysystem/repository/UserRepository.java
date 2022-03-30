package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findByFirstnameAndLastname(String firstname, String lastname);

    List<UserEntity> findByFirstname(String firstname);

    List<UserEntity> findByLastname(String lastname);

    UserEntity findBySsn(String ssn);

}
