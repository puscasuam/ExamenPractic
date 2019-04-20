package sample.Domain;

import java.util.ArrayList;
import java.util.List;

public class Car extends Entity {

    private String model;
    private int baseKm;
    private double rentPrice;

    public Car(int id, String model, int baseKm, double rentPrice) {
        super(id);
        this.model = model;
        this.baseKm = baseKm;
        this.rentPrice = rentPrice;
    }


    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", basekm=" + baseKm +
                ", rentPrice=" + rentPrice +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBaseKm() {
        return baseKm;
    }

    public void setBaseKm(int baseKm) {
        this.baseKm = baseKm;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public List<String> getAllFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(toString().valueOf(this.getModel()));
        fields.add(toString().valueOf(this.getBaseKm()));
        fields.add(toString().valueOf(this.getRentPrice()));
        return fields;
    }


}
