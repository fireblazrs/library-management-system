package se.iths.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Task {

    private Long id;
    private String url;
    private String status;
    private boolean success;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime registered;

    public Task() {
    }

    public Task(String path) {
        this.url = path;
    }

    public Task(Long id, String url, String status, boolean success, LocalDateTime registered) {
        this.id = id;
        this.url = url;
        this.status = status;
        this.success = success;
        this.registered = registered;
    }

    public Task(Long id, String status, boolean success, LocalDateTime registered) {
        this.id = id;
        this.status = status;
        this.success = success;
        this.registered = registered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
