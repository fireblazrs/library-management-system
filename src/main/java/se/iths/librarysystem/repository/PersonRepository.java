package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.PersonEntity;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findByFirstnameAndLastname(String firstname, String lastname);

    PersonEntity findByFirstname(String firstname);

    PersonEntity findByLastname(String lastname);

    PersonEntity findBySsn(String ssn);

}
