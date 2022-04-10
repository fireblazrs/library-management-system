package se.iths.librarysystem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class BookFormatEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String formatName;
    private boolean digital;
    private int pageCount;
    private String length;

    @ManyToMany
    @JoinTable(
            name = "book_enrolled_bookFormat",
            joinColumns = @JoinColumn(name = "bookFormat_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")

    )
    private List<BookEntity> enrolledBooks1 = new ArrayList<>();


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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<BookEntity> getEnrolledBooks1() {
        return enrolledBooks1;
    }

    public void setEnrolledBooks1(BookEntity bookEntity) {
        enrolledBooks1.add(bookEntity);
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
