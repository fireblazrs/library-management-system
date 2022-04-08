package se.iths.librarysystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.librarysystem.entity.RoomEntity;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.repository.RoomRepository;
import se.iths.librarysystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public RoomEntity createRoom(RoomEntity roomEntity){
        return roomRepository.save(roomEntity);
    }

    public List<RoomEntity> getAllRooms() {
        return StreamSupport
                .stream(roomRepository.findAll().spliterator(), false)
                .toList();
    }

    public List<RoomEntity> findRoomByName(String name) {
        return roomRepository.findByName(name);
    }

    public Optional<RoomEntity> findById(Long id) {
        return roomRepository.findById(id);
    }

    public RoomEntity updateRoom(RoomEntity roomEntity){
        return roomRepository.save(roomEntity);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Transactional
    public RoomEntity addUserToRoom(Long roomId, Long userId) {
        RoomEntity room = roomRepository.findById(roomId).orElseThrow(() -> new IdNotFoundException("room", roomId));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException("user", userId));
        room.setUser(user);
        return room;
    }
}

