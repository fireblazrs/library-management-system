package se.iths.librarysystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class BookFormatEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String format;

    public BookFormatEntity(){
    }

    public BookFormatEntity(String format) {
        this.format = format;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFormat() {
        return format;
    }

    public BookFormatEntity setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookFormatEntity that)) return false;
        return Objects.equals(format, that.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(format);
    }
}
