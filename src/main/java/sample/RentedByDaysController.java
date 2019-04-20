package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Domain.RentedByDaysModelView;
import sample.Service.BookingService;

public class RentedByDaysController {
    public TableView tableViewRentedByDays;
    public TableColumn tableColumnId;
    public TableColumn tableColumnModel;
    public TableColumn tableColumnRentedDays;
    private BookingService bookingService;


    public void setService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private ObservableList<RentedByDaysModelView> rented = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            rented.addAll(bookingService.getRentalByDaysReport());
            tableViewRentedByDays.setItems(rented);
        });
    }
}