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
    private String formatName;
    private boolean digital;
    private int pageCount;
    private String length;


    public BookFormatEntity(){
    }

    public BookFormatEntity(String formatName, boolean digital) {
        this(formatName,digital,0,"");
    }

    public BookFormatEntity(String formatName, boolean digital, int pageCount) {
        this(formatName,digital,pageCount,"");
    }

    public BookFormatEntity(String formatName, boolean digital, String length) {
        this(formatName,digital,0,length);
    }

    public BookFormatEntity(String formatName, boolean digital, int pageCount, String length) {
        this.formatName = formatName;
        this.digital = digital;
        this.pageCount = pageCount;
        this.length = length;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFormatName() {
        return formatName;
    }

    public BookFormatEntity setFormatName(String format) {
        this.formatName = format;
        return this;
    }

    public boolean isDigital() {
        return digital;
    }

    public BookFormatEntity setDigital(boolean digital) {
        this.digital = digital;
        return this;
    }

    public int getPageCount() {
        return pageCount;
    }

    public BookFormatEntity setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public String getLength() {
        return length;
    }

    public BookFormatEntity setLength(String length) {
        this.length = length;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookFormatEntity that)) return false;
        return digital == that.digital && pageCount == that.pageCount &&
            Objects.equals(formatName, that.formatName) &&
            Objects.equals(length, that.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatName, digital, pageCount, length);
    }
}
