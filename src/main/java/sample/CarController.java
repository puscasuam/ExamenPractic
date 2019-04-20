package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Domain.Car;
import sample.Service.CarService;

public class CarController {

    public TableColumn tableColumnId;

    public TableView tableViewCars;
    public TableColumn tableColumnModel;
    public TableColumn tableColumnBaseKm;
    public TableColumn tableColumnRentPrice;
    public TextField txtCarId;
    public TextField txtCarModel;
    public TextField txtCarBaseKm;
    public TextField txtCarRentPrice;
    public Button btnAddCar;
    


    private CarService carService;

    public void setService(CarService carService) {
        this.carService = carService;
    }

    private ObservableList<Car> cars = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            cars.addAll(carService.getAll());
            tableViewCars.setItems(cars);
        });
    }

    public void btnAddCar(ActionEvent actionEvent) {
        try {
            Integer id = Integer.parseInt(txtCarId.getText());
            String model = txtCarModel.getText();
            Integer baseKm = Integer.parseInt(txtCarBaseKm.getText());
            double rentPrice = Double.parseDouble(txtCarRentPrice.getText());

            carService.add(id, model, baseKm, rentPrice);

            cars.clear();
            cars.addAll(carService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

//    public void btSumForDayClick(ActionEvent actionEvent) {
//        try {
//            String date = txtSumDay.getText();
//            double sum = invoiceService.getSumDay(date);
//            txtSumResult.setText(String.valueOf(sum));
//
//        } catch (RuntimeException rex) {
//            Common.showValidationError(rex.getMessage());
//        }
//    }


    public void btnCarUndoClick(ActionEvent actionEvent) {
        carService.undo();
        cars.clear();
        cars.addAll(carService.getAll());
    }

    public void btnCarRedoClick(ActionEvent actionEvent) {
        carService.redo();
        cars.clear();
        cars.addAll(carService.getAll());
    }

}