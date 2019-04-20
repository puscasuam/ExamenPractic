package sample.Service;

import sample.Domain.Booking;
import sample.Domain.Car;
import sample.Domain.CarIdException;
import sample.Domain.InvoicesSumByClientModelView;
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

//    public List<InvoicesSumByClientModelView> getClientsReport() {
//
//        List<InvoicesSumByClientModelView> sumByClients = new ArrayList<>();
//
//        Map<String, Double> sumOfInvoices = new HashMap<>();
//
//        for (Company companyI : companyRepository.getAll()) {
//            String clientI = companyI.getClient();
//            int clientInvoiceId = companyI.getInvoiceId();
//            Invoice existingInvoice = invoiceRepository.findById(clientInvoiceId);
//
//            if (!sumOfInvoices.containsKey(clientI)) {
//                sumOfInvoices.put(clientI, existingInvoice.getSum());
//            } else {
//                sumOfInvoices.replace(clientI, sumOfInvoices.get(clientI) + existingInvoice.getSum());
//            }
//        }


//        for(String key : sumOfInvoices.keySet()){
//            InvoicesSumByClientModelView sumByClient = new InvoicesSumByClientModelView(key, sumOfInvoices.get(key));
//            sumByClients.add(sumByClient);
//        }
//
//        return sumByClients;
//    }

    /**
     * Gets a list of all invoices.
     *
     * @return a list of all invoices.
     */
    public List<Booking> getAll() {
        return bookingRepository.getAll();
    }
}
