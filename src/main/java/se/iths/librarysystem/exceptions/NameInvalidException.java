package se.iths.librarysystem.exceptions;

public class NameInvalidException extends RuntimeException {

    private String path;

    public NameInvalidException(String entity, String name) {
        super(entity + " with name " + name + " not found.");
        path = "/" + entity + "s/" + name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
