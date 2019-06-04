package model;

import java.util.ArrayList;

public class Person extends Entity{
    private String gender;
    private int age;
    public Person(){

    }

    public Person(String id, String label, String describe, ArrayList<Origin> origins, String gender, int age) {
        super(id, label, describe, origins);
        this.gender = gender;
        this.age = age;
    }
    public Person(Entity entity){
        super(entity.getId(), entity.getLabel(), entity.getDescribe(), entity.getOrigins());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
