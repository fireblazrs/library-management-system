package se.iths.librarysystem.exceptions;

public class InvalidInputException extends RuntimeException {

    private String path;

    public InvalidInputException(String message, String path) {
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
