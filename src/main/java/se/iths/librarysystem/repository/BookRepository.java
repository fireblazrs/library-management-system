package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findByIsbn(String isbn);
}
