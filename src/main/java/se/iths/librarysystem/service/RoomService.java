package se.iths.librarysystem.service;

import se.iths.librarysystem.entity.RoomEntity;
import se.iths.librarysystem.repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService (RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomEntity createRoom(RoomEntity roomEntity){
        return roomRepository.save(roomEntity);
    }

    public List<RoomEntity> getAllRooms() {
        return StreamSupport
                .stream(roomRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<RoomEntity> getRoomByName(String name) {
        return roomRepository.findByName(name);
    }

    public Optional<RoomEntity> getById(Long id) {
        return roomRepository.findById(id);
    }

    public RoomEntity updateRoom(RoomEntity roomEntity){
        return roomRepository.save(roomEntity);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}

