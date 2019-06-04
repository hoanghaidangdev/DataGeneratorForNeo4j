package model;

import java.util.ArrayList;

public class Event extends Entity {
    private String venue;

    public Event() {

    }

    public Event(String id, String label, String describe, ArrayList<Origin> origins, String venue) {
        super(id, label, describe, origins);
        this.venue = venue;
    }
    public Event(Entity entity){
        super(entity.getId(), entity.getLabel(), entity.getDescribe(), entity.getOrigins());
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
