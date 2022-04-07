package se.iths.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Task setPath(String path) {
        this.path = path;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Task setStatus(String status) {
        this.status = status;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Task setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public Task setRegistered(LocalDateTime registered) {
        this.registered = registered;
        return this;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Task setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return success == task.success && completed == task.completed
               && Objects.equals(id, task.id) && Objects.equals(path, task.path)
               && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, status, success, completed);
    }
}
