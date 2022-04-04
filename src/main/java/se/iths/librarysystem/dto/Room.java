package se.iths.librarysystem.dto;

public class Room {

    private Long id;
    private String name;
    private int groupSize;
    private boolean internetAccess;
    private boolean wheelchairAccess;
    private boolean hasProjector;

    public Room(){

    }

    public Room(String name, int groupSize, boolean internetAccess,
                boolean wheelchairAccess, boolean hasProjector) {
        this.name = name;
        this.groupSize = groupSize;
        this.internetAccess = internetAccess;
        this.wheelchairAccess = wheelchairAccess;
        this.hasProjector = hasProjector;
    }

    public Room(String name, int groupSize, boolean wheelchairAccess) {
        this(name, groupSize, Boolean.TRUE, wheelchairAccess, Boolean.FALSE);
    }

    public Long getId() {
        return id;
    }

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public Room setGroupSize(int groupSize) {
        this.groupSize = groupSize;
        return this;
    }

    public boolean isInternetAccess() {
        return internetAccess;
    }

    public Room setInternetAccess(boolean internetAccess) {
        this.internetAccess = internetAccess;
        return this;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public Room setWheelchairAccess(boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
        return this;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public Room setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
        return this;
    }
}
