package sample.Service;

import sample.Domain.Booking;
import sample.Domain.Car;
import sample.Domain.CarIdException;
import sample.Domain.RentedByDaysModelView;
import sample.Repository.IRepository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

    private IRepository<Booking> bookingRepository;
    private IRepository<Car> carRepository;

    public BookingService(IRepository<Booking> bookingRepository, IRepository<Car> carRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
    }

    /**
     * Construct
     * @param id
     * @param carID
     * @param rentaldays
     * @param usedKm
     */
    public void add(int id, int carID, int rentaldays, int usedKm) {

        Car existingCar = carRepository.findById(carID);
        if (existingCar == null) {
            throw new CarIdException("There is no car with the given id!");
        }

        Booking booking = new Booking(id, carID, rentaldays, usedKm);
        bookingRepository.upsert(booking);
    }


    public List<RentedByDaysModelView> getRentalByDaysReport() {

        List<RentedByDaysModelView> rentedCars = new ArrayList<>();

        Map<Integer, Integer> frequencies = new HashMap<>();

        for (Booking bookingX : bookingRepository.getAll()) {
            int carIdX = bookingX.getCarID();
            int rentedDaysX = bookingX.getRentalDays();

            if (!frequencies.containsKey(carIdX)) {
                frequencies.put(carIdX, rentedDaysX);
            } else {
                frequencies.replace(carIdX, frequencies.get(carIdX) + rentedDaysX);
            }
        }


        for (Integer key : frequencies.keySet()) {
            int carIdX = key;

            for (Car carX : carRepository.getAll()) {
                if (carX.getId() == carIdX) {
                    RentedByDaysModelView rentedCar = new RentedByDaysModelView(carX.getId(), carX.getModel(), frequencies.get(key));
                    rentedCars.add(rentedCar);
                }
            }
        }

        rentedCars.sort((v1, v2) -> v1.getRentDays() > v2.getRentDays() ? -1 : 0);

        return rentedCars;
    }


    public double getValueReport(Integer carId) {
        double value = 0;

        Car existingCar = carRepository.findById(carId);

        if (existingCar == null) {
            throw new CarIdException("There is no car with the given id!");
        }

        Map<Integer, Integer> frequencies = new HashMap<>();

        for (Booking bookingX : bookingRepository.getAll()) {
            int carIdX = bookingX.getCarID();
            int rentedDaysX = bookingX.getRentalDays();

            if (!frequencies.containsKey(carIdX)) {
                frequencies.put(carIdX, rentedDaysX);
            } else {
                frequencies.replace(carIdX, frequencies.get(carIdX) + rentedDaysX);
            }
        }

        for (Integer key : frequencies.keySet()) {
            int carIdX = key;

            if (carIdX == carId) {
                value = frequencies.get(key) * existingCar.getRentPrice();
            }
        }

        return value;
    }

    public int getKm(Integer carId) {
        int km = 0;

        Car existingCar = carRepository.findById(carId);

        if (existingCar == null) {
            throw new CarIdException("There is no car with the given id!");
        }

        Map<Integer, Integer> totalKmByRent = new HashMap<>();

        for (Booking bookingX : bookingRepository.getAll()) {
            int carIdX = bookingX.getCarID();
            int usedKmByRent = bookingX.getUsedKm();

            if (!totalKmByRent.containsKey(carIdX)) {
                totalKmByRent.put(carIdX, usedKmByRent);
            } else {
                totalKmByRent.replace(carIdX, totalKmByRent.get(carIdX) + usedKmByRent);
            }
        }

        for (Integer key : totalKmByRent.keySet()) {
            int carIdX = key;

            if (carIdX == carId) {
                km = totalKmByRent.get(key) + existingCar.getBaseKm();
            }
        }

        return km;
    }


    /**
     * Gets a list of all invoices.
     *
     * @return a list of all invoices.
     */
    public List<Booking> getAll() {
        return bookingRepository.getAll();
    }
}
