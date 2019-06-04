package model;

import java.util.ArrayList;

public class Time extends Entity{
    public Time(){

    }

    public Time(String id, String label, String describe, ArrayList<Origin> origins) {
        super(id, label, describe, origins);
    }
    public Time(Entity entity){
        super(entity.getId(), entity.getLabel(), entity.getDescribe(), entity.getOrigins());
    }
}
