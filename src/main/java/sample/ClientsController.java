//package sample;
//
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import sample.Domain.InvoicesSumByClientModelView;
//import sample.Service.BookingService;
//
//public class ClientsController {
//    public TableView tableViewClients;
//    public TableColumn tableColumnClient;
//    public TableColumn tableColumnSum;
//    public BookingService companyService;
//
//
//    public void setService(BookingService companyService) {
//        this.companyService = companyService;
//    }
//
//    private ObservableList<InvoicesSumByClientModelView> sumByClients = FXCollections.observableArrayList();
//
//    @FXML
//    private void initialize() {
//        Platform.runLater(() -> {
//            sumByClients.addAll(companyService.getClientsReport());
//            tableViewClients.setItems(sumByClients);
//        });
//    }
//}