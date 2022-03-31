package se.iths.librarysystem.dto;

public class Isbn {

    private String value;

    public Isbn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
