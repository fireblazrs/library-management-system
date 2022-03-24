package se.iths.librarysystem.validatorservice;

public abstract class LibraryValidator {

    public LibraryValidator() {
    }

    public void validId(Long id) {
        if(id == null || id < 1L)
            throw new IllegalArgumentException(id + " is an invalid Id."); //todo: InvalidValueException
    }

    public abstract void idExists(Long id);
}
