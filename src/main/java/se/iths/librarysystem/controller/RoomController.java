package se.iths.librarysystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.librarysystem.dto.User;
import se.iths.librarysystem.dto.Room;
import se.iths.librarysystem.entity.UserEntity;
import se.iths.librarysystem.entity.RoomEntity;
import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.ValueNotFoundException;
import se.iths.librarysystem.service.RoomService;
import se.iths.librarysystem.validatorservice.RoomValidator;
import se.iths.librarysystem.validatorservice.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rooms")
public class RoomController {

    private final RoomService roomService;
    private final ModelMapper modelMapper;
    private final RoomValidator roomValidator;
    private final UserValidator userValidator;


    public RoomController(RoomService roomService, ModelMapper modelMapper,
                          RoomValidator roomValidator, UserValidator userValidator) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
        this.roomValidator = roomValidator;
        this.userValidator = userValidator;
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
        roomValidator.validId(id);

        RoomEntity roomEntity = roomService.findById(id).orElseThrow(() -> new IdNotFoundException("room", id));
        Room room = modelMapper.map(roomEntity, Room.class);

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room room) {
        roomValidator.validId(room.getId());
        roomValidator.idExists(room.getId());

        RoomEntity roomEntity = modelMapper.map(room, RoomEntity.class);
        RoomEntity updatedRoomEntity = roomService.updateRoom(roomEntity);
        Room updatedRoom = modelMapper.map(updatedRoomEntity, Room.class);

        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long id) {
        roomValidator.validId(id);

        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("getbyname")
    public ResponseEntity<List<Room>> findRoomsByName(@RequestParam String name){
        roomValidator.validName(name);

        List<RoomEntity> roomEntities = roomService.findRoomByName(name);
        List<Room> rooms = new ArrayList<>();
        roomEntities.forEach(roomEntity -> rooms.add(modelMapper.map(roomEntity, Room.class)));

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("{id}/user")
    public ResponseEntity<User> getRoomUser(@PathVariable Long id) {
        roomValidator.validId(id);
        RoomEntity roomEntity = roomService.findById(id).orElseThrow(() -> new IdNotFoundException("room", id));
        UserEntity userEntity = Optional.ofNullable(roomEntity.getUser())
            .orElseThrow(() -> new ValueNotFoundException("user", "/rooms/" + id + "/user"));
        User user = modelMapper.map(userEntity, User.class);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("{roomId}/user/{userId}")
    public ResponseEntity<Room> updateRoomRoom(@PathVariable Long roomId, @PathVariable Long userId) {
        roomValidator.validId(roomId);
        userValidator.validId(userId);

        RoomEntity roomEntity = roomService.addUserToRoom(roomId, userId);
        Room room = modelMapper.map(roomEntity, Room.class);

        return new ResponseEntity<>(room, HttpStatus.OK);
    }





}
