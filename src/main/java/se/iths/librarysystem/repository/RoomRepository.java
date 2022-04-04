package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.RoomEntity;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByName(String name);
}
