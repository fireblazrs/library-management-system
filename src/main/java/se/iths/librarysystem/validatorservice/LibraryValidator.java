package se.iths.librarysystem.validatorservice;

public abstract class LibraryValidator {

    public LibraryValidator() {
    }

    public abstract void validId(Long id);

    public abstract void idExists(Long id);
}
