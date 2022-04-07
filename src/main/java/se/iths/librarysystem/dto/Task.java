package se.iths.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Task {

    private Long id;
    private String path;
    private String status;
    private boolean success;
    private boolean completed;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime registered;

    public Task() {
    }

    public Task(String path, String status, boolean success, boolean completed, LocalDateTime registered) {
        this.path = path;
        this.status = status;
        this.success = success;
        this.completed = completed;
        this.registered = registered;
    }

    public Task(String status, boolean success, boolean completed, LocalDateTime registered) {
        this("", status, success, completed, registered);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
