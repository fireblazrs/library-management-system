package se.iths.librarysystem.exceptions;

public class ValueNotFoundException extends RuntimeException {

    private String path;

    public ValueNotFoundException(String entity, String path) {
        super(entity + " is null and requires a value!");
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
