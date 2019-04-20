package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Domain.Booking;
import sample.Service.BookingService;


public class BookingController {

    public TableColumn tableColumnId;

    public TableView tableViewBookings;
    public TableColumn tableColumnCarId;
    public TableColumn tableColumnRentalDays;
    public TableColumn tableColumnUsedKm;
    public TextField txtBookingId;
    public TextField txtBookingCarId;
    public TextField txtBookingRentalDays;
    public TextField txtBookingUsedKm;
    public Button btnAddBooking;
    public TextField txtCarId;
    public TextField txtValueResult;
    public Button btnRentalIncome;
    


    private BookingService bookingService;


    public void setService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private ObservableList<Booking> bookings = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            bookings.addAll(bookingService.getAll());
            tableViewBookings.setItems(bookings);
        });
    }


    public void btnAddBooking(ActionEvent actionEvent) {
        try {
            Integer id = Integer.parseInt(txtBookingId.getText());
            Integer carID = Integer.parseInt(txtBookingCarId.getText());
            Integer rentalDays = Integer.parseInt(txtBookingRentalDays.getText());
            Integer usedKm = Integer.parseInt(txtBookingUsedKm.getText());

            bookingService.add(id, carID, rentalDays,usedKm);

            bookings.clear();
            bookings.addAll(bookingService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }


    public void btRentalIncomeClick(ActionEvent actionEvent) {
        try {
            Integer id = Integer.parseInt(txtCarId.getText());
            double value = bookingService.getValueReport(id);
            txtValueResult.setText(String.valueOf(value));

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

    }
}
