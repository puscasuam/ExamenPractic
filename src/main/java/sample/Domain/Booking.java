package sample.Domain;

import java.util.ArrayList;
import java.util.List;

public class Booking extends Entity {
    private int carID;
    private int rentalDays;
    private int usedKm;

    /**
     * Construct a booking
     * @param id - the id
     * @param carID - the car ID
     * @param rentalDays - the rented days
     * @param usedKm - used km
     */
    public Booking(int id, int carID, int rentalDays, int usedKm) {
        super(id);
        this.carID = carID;
        this.rentalDays = rentalDays;
        this.usedKm = usedKm;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getUsedKm() {
        return usedKm;
    }

    public void setUsedKm(int usedKm) {
        this.usedKm = usedKm;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "carID=" + carID +
                ", rentaldays=" + rentalDays +
                ", usedKm=" + usedKm +
                '}';
    }

    @Override
    public List<String> getAllFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(toString().valueOf(this.getCarID()));
        fields.add(toString().valueOf(this.getRentalDays()));
        fields.add(toString().valueOf(this.getUsedKm()));
        return fields;
    }
}
