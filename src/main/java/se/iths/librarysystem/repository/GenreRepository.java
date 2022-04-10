package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.GenreEntity;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity, Long> {

}
