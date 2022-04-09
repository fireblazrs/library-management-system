package se.iths.librarysystem.dto;

public class Genre {

    private Long id;
    private String genreName;
    private boolean fiction;

    public Genre() {
    }

    public Genre(String genreName, boolean fiction) {
        this.genreName = genreName;
        this.fiction = fiction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public boolean isFiction() {
        return fiction;
    }

    public void setFiction(boolean fiction) {
        this.fiction = fiction;
    }
}
