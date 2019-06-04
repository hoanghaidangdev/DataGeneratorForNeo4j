package model;

import java.util.ArrayList;

public class Agreement extends Entity{
    public Agreement(){

    }

    public Agreement(String id, String label, String describe, ArrayList<Origin> origins) {
        super(id, label, describe, origins);
    }
    public Agreement(Entity entity){
        super(entity.getId(), entity.getLabel(), entity.getDescribe(), entity.getOrigins());
    }
}
