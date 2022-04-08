package se.iths.librarysystem.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Room name is a required field")
    private String name;
    
    private int groupSize;
    private boolean internetAccess;
    private boolean wheelchairAccess;
    private boolean hasProjector;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;


    public RoomEntity() {

    }

    public RoomEntity(String name, int groupSize, boolean internetAccess, boolean wheelchairAccess, boolean hasProjector) {
        this.name = name;
        this.groupSize = groupSize;
        this.internetAccess = internetAccess;
        this.wheelchairAccess = wheelchairAccess;
        this.hasProjector = hasProjector;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public boolean isInternetAccess() {
        return internetAccess;
    }

    public void setInternetAccess(boolean internetAccess) {
        this.internetAccess = internetAccess;
    }

    public boolean isWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(boolean wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public UserEntity getUser() {
        return user;
    }

    public RoomEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
