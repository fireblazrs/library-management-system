package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.LoanTaskEntity;

@Repository
public interface LoanTaskRepository extends CrudRepository<LoanTaskEntity, Long> {

}
