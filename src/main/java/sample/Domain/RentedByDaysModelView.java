package sample.Domain;

public class RentedByDaysModelView {
    private int id;
    private String model;
    private int rentDays;

    public RentedByDaysModelView(int id, String model, int rentDays) {
        this.id = id;
        this.model = model;
        this.rentDays = rentDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRentDays() {
        return rentDays;
    }

    public void setRentDays(int rentDays) {
        this.rentDays = rentDays;
    }

}

