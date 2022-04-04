package se.iths.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.librarysystem.entity.RoomEntity;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    RoomEntity findByName(String name);
}
