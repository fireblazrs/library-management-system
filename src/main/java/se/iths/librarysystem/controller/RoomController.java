package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.dto.Room;
import se.iths.librarysystem.entity.RoomEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.NameNotFoundException;
import se.iths.librarysystem.service.RoomService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {

    private final RoomService roomService;
    private final ModelMapper modelMapper;

    public RoomController(RoomService roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room){
        RoomEntity roomEntity = modelMapper.map(room, RoomEntity.class);
        RoomEntity createdEntity = roomService.createRoom(roomEntity);
        Room createdRoom = modelMapper.map(createdEntity, Room.class);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Room>> getAllRooms(){
    Iterable<RoomEntity> roomEntities = roomService.getAllRooms();
    List<Room> rooms = new ArrayList<>();
        roomEntities.forEach(roomEntity -> rooms.add(modelMapper.map(roomEntity, Room.class)));
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable Long id){
        RoomEntity roomEntity = roomService.findById(id).orElseThrow(() -> new IdNotFoundException("room", id));
        Room room = modelMapper.map(roomEntity, Room.class);

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room room) {
        RoomEntity roomEntity = modelMapper.map(room, RoomEntity.class);
        RoomEntity updatedRoomEntity = roomService.updateRoom(roomEntity);
        Room updatedRoom = modelMapper.map(updatedRoomEntity, Room.class);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
