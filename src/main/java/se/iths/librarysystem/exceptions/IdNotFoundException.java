package se.iths.librarysystem.exceptions;

public class IdNotFoundException extends RuntimeException {

    private String path;

    public IdNotFoundException(String entity, Long id) {
        super(entity + " with Id " + id + " not found.");
        path = "/" + entity + "s/" + id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
