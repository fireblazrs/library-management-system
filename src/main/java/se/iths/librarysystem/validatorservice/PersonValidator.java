package se.iths.librarysystem.validatorservice;

import se.iths.librarysystem.exceptions.IdNotFoundException;
import se.iths.librarysystem.service.PersonService;

public class PersonValidator extends LibraryValidator {

    PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public void idExists(Long id) {
        personService.findById(id).orElseThrow(() -> new IdNotFoundException("user", id));
    }
}
