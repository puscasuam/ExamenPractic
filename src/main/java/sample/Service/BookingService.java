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


    public void add(int id, int carID, int rentaldays, int usedKm) {

        Car existingCar = carRepository.findById(carID);
        if (existingCar == null) {
            throw new CarIdException("There is no car with the given id!");
        }

        Booking booking = new Booking(id, carID, rentaldays, usedKm);
        bookingRepository.upsert(booking);
    }


//    public void remove(Integer id) throws CompanyException {
//        Company existingCompany = companyRepository.findById(id);
//
//        if (existingCompany == null) {
//            throw new CompanyException("There is no company with the given id!");
//        }
//        companyRepository.remove(id);
//    }

    public List<RentedByDaysModelView> getRentalByDaysReport() {

        List<RentedByDaysModelView> rentedCars = new ArrayList<>();

        Map<Integer, Integer> frequencies = new HashMap<>();

        for (Booking bookingX : bookingRepository.getAll()) {
            int carIdX = bookingX.getCarID();
            int rentedDaysX = bookingX.getRentalDays();
           // int clientInvoiceId = companyI.getInvoiceId();
            //Car existingCar = carRepository.findById(clientInvoiceId);

            if (!frequencies.containsKey(carIdX)) {
                frequencies.put(carIdX, rentedDaysX);
            } else {
                frequencies.replace(carIdX, frequencies.get(carIdX) + rentedDaysX);
            }
        }


        for(Integer key : frequencies.keySet()){
            int carIdX = key;

            for(Car carX : carRepository.getAll()){
                if(carX.getId() == carIdX){
                    RentedByDaysModelView rentedCar = new RentedByDaysModelView(carX.getId(), carX.getModel(),frequencies.get(key));
                    rentedCars.add(rentedCar);
                }
            }
        }

        rentedCars.sort((v1, v2) -> v1.getRentDays() > v2.getRentDays() ? -1 : 0);

        return rentedCars;
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
