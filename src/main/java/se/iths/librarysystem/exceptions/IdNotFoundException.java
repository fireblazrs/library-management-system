package se.iths.librarysystem.exceptions;

public class IdNotFoundException extends RuntimeException {

    private final String path;

    public IdNotFoundException(String entity, Long id) {
        super(entity + " with Id " + id + " not found.");
        path = "/" + entity + "s/" + id;
    }

    public String getPath() {
        return path;
    }

}
