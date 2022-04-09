package se.iths.librarysystem.dto;

public class BookFormat {

    private Long id;
    private String formatName;
    private boolean digital;
    private int pageCount;
    private String length;

    public BookFormat() {
    }

    public BookFormat(Long id, String formatName, boolean digital, int pageCount, String length) {
        this.id = id;
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

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
