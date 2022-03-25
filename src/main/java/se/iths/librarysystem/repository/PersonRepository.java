package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.PersonEntity;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    List<PersonEntity> findByFirstnameAndLastname(String firstname, String lastname);

    List<PersonEntity> findByFirstname(String firstname);

    List<PersonEntity> findByLastname(String lastname);

    PersonEntity findBySsn(String ssn);

}
