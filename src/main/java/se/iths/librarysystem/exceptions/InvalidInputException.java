package se.iths.librarysystem.exceptions;

public class InvalidInputException extends RuntimeException {

    private final String path;

    public InvalidInputException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
