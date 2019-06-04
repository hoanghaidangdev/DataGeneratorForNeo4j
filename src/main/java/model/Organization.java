package model;

import java.util.ArrayList;

public class Organization extends Entity {
    private String headquarters;
    private String website;

    public Organization() {

    }

    public Organization(String id, String label, String describe, ArrayList<Origin> origins, String headquarters, String website) {
        super(id, label, describe, origins);
        this.headquarters = headquarters;
        this.website = website;
    }
    public Organization(Entity entity){
        super(entity.getId(), entity.getLabel(), entity.getDescribe(), entity.getOrigins());
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
