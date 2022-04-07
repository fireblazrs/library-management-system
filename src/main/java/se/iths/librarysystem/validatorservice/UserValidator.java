package se.iths.librarysystem.validatorservice;

import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.InvalidInputException;
import se.iths.librarysystem.service.UserService;

public class UserValidator extends LibraryValidator {

    UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validId(Long id) {
        if(id == null || id < 1L)
            throw new InvalidInputException(id + " is an invalid id.", "/users/");
    }

    @Override
    public void idExists(Long id) {
        userService.findUserEntityById(id).orElseThrow(() -> new IdNotFoundException("user", id));
    }
}
