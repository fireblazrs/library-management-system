package se.iths.librarysystem.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiErrors {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy hh:mm:ss")
    private LocalDateTime timestamp;
    private Map<String, String> errors;
    private String path;

    public ApiErrors() {
        timestamp = LocalDateTime.now();
    }

    public ApiErrors(HttpStatus status, Map<String, String> errors, String path) {
        this();
        this.status = status;
        this.errors = errors;
        this.path = path;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
