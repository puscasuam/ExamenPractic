package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Service.BookingService;
import sample.Service.CarService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    public Button btnCar;
    public Button btnBooking;
    public Button btnRentedByDaysMV;


    private CarService carService;
    private BookingService bookingService;


    public void setServices(CarService carService, BookingService bookingService) {
        this.carService = carService;
        this.bookingService = bookingService;
    }


    public void btnCarClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Car.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Car");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            CarController controller = fxmlLoader.getController();
            controller.setService(carService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Car operations.", e);
        }

    }

    public void btnBookingClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Booking.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Booking");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            BookingController controller = fxmlLoader.getController();
            controller.setService(bookingService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Booking operations.", e);
        }
    }

    public void btnRentedByDaysMVClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/RentedByDays.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            Stage stage = new Stage();
            stage.setTitle("Rentals report view");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            RentedByDaysController controller = fxmlLoader.getController();
            controller.setService(bookingService);

            stage.showAndWait();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Rentals report view.", e);
        }
    }
}
