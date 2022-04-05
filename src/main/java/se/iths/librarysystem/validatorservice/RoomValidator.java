package se.iths.librarysystem.validatorservice;

import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.InvalidInputException;
import se.iths.librarysystem.service.RoomService;

public class RoomValidator extends LibraryValidator {

    private final RoomService roomService;

    public RoomValidator(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void validId(Long id) {
        if (id == null || id < 1L)
            throw new InvalidInputException(id + " is an invalid id.", "/rooms/");
    }

    @Override
    public void idExists(Long id) {
        roomService.findById(id).orElseThrow(() -> new IdNotFoundException("room", id));
    }
}
