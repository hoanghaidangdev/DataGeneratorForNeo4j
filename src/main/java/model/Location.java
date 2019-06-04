package model;

import java.util.ArrayList;

public class Location extends Entity {
    private String city;

    public Location() {

    }

    public Location(String id, String label, String describe, ArrayList<Origin> origins, String city) {
        super(id, label, describe, origins);
        this.city = city;
    }

    public Location(Entity entity) {
        super(entity.getId(), entity.getLabel(), entity.getDescribe(), entity.getOrigins());
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
