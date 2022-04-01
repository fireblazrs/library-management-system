package se.iths.librarysystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class LoanTaskEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private Long userId;
    private String status;
    private boolean success;
    private boolean completed;
    private LocalDateTime registered;
    private String message;

    public LoanTaskEntity() {

    }

    public LoanTaskEntity(String isbn, Long userId) {
        super();
        this.isbn = isbn;
        this.userId = userId;
    }

    @PrePersist
    private void setInitialValues() {
        registered = LocalDateTime.now();
        status = "pending";
        message = "";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public void taskPending() {
        setStatus("pending");
    }

    public void taskComplete() {
        setStatus("complete");
        setCompleted(true);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean complete) {
        this.success = complete;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanTaskEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(isbn, that.isbn)
               && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, userId);
    }
}
