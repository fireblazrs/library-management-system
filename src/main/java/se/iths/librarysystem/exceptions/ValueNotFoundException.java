package se.iths.librarysystem.exceptions;

public class ValueNotFoundException extends RuntimeException {

    private final String path;

    public ValueNotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
