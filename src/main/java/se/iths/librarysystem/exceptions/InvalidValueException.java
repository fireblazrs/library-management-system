package se.iths.librarysystem.exceptions;

public class InvalidValueException extends RuntimeException {

    private String path;

    public InvalidValueException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
