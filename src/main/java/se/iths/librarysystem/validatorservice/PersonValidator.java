package se.iths.librarysystem.validatorservice;

import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.exceptions.InvalidValueException;
import se.iths.librarysystem.service.PersonService;

public class PersonValidator extends LibraryValidator {

    PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void validId(Long id) {
        if(id == null || id < 1L)
            throw new InvalidValueException(id + " is an invalid id.", "/users/");
    }

    @Override
    public void idExists(Long id) {
        personService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
    }
}
