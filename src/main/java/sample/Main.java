package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Domain.*;
import sample.Repository.FileRepository;
import sample.Repository.IRepository;
import sample.Service.BookingService;
import sample.Service.CarService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent root = fxmlLoader.load();

        IValidator<Car> carValidator = new CarValidator();
        IRepository<Car> carIRepository= new FileRepository<>(carValidator, "cars.json", Car[].class);

        CarService carService = new CarService(carIRepository);
        carService.add(1, "audi", 15000, 20.0);
        carService.add(2, "audi", 10000, 25.0);
        carService.add(3, "dacia", 30000, 15.0);


        IValidator<Booking> bookingIValidator = new BookingValidator();
        IRepository<Booking> bookingIRepository= new FileRepository<>(bookingIValidator, "bookings.json", Booking[].class);
        BookingService bookingService = new BookingService(bookingIRepository, carIRepository);


        MainController controller =  fxmlLoader.getController();
        controller.setServices(carService, bookingService);

        primaryStage.setTitle("Aplication manager");
        primaryStage.setScene(new Scene(root, 650, 500));

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
