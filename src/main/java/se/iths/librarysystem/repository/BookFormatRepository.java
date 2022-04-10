package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.BookFormatEntity;

@Repository
public interface BookFormatRepository extends CrudRepository<BookFormatEntity, Long> {

}
