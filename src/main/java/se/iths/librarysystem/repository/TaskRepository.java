package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.TaskEntity;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

}
