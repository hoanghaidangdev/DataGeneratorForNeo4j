package model;

import java.util.ArrayList;

public class Country extends Entity {
    private String capital;
    private int population;

    public Country() {

    }

    public Country(String id, String label, String describe, ArrayList<Origin> origins, String capital, int population) {
        super(id, label, describe, origins);
        this.capital = capital;
        this.population = population;
    }
    public Country(Entity entity){
        super(entity.getId(), entity.getLabel(), entity.getDescribe(), entity.getOrigins());
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
