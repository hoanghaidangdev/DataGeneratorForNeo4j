package model;


import java.util.ArrayList;

public class Entity {
    private String id;
    private String label;
    private String describe;
    private ArrayList<Origin> origins;

    public Entity() {
        origins = new ArrayList<Origin>();
    }

    public Entity(String id, String label, String describe, ArrayList<Origin> origins) {
        this.id = id;
        this.label = label;
        this.describe = describe;
        this.origins = origins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public ArrayList<Origin> getOrigins() {
        return origins;
    }

    public void setOrigins(ArrayList<Origin> origins) {
        this.origins = origins;
    }

}
