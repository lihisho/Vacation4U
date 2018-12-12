package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import vacationClasses.flight;
import Controller.Controller;

import javafx.scene.control.TableView;
import vacationClasses.flightSearchColumn;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class flightResultsView extends AView implements Initializable{
    private Controller controller = Controller.getInstance();

    public TableView<flightSearchColumn> tableResults;

    public TableColumn<flightSearchColumn, String> from;
    public TableColumn<flightSearchColumn, String> destination;
    public TableColumn<flightSearchColumn, String> departDate;
    public TableColumn<flightSearchColumn, String> returnDate;
    public TableColumn<flightSearchColumn, String> priceCol;
    public TableColumn<flightSearchColumn, String> supplier;
    public TableColumn<flightSearchColumn, String> buyCol;
    public TableColumn<flightSearchColumn, String> detailsCol;
    public Button btn_returnHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));

        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        departDate.setCellValueFactory(new PropertyValueFactory<>("departDate"));

        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        supplier.setCellValueFactory(new PropertyValueFactory<>("supplierUserName"));

        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        detailsCol.setCellValueFactory(new PropertyValueFactory<>("btnViewFlight"));

        buyCol.setCellValueFactory(new PropertyValueFactory<>("btnBuy"));

        // buycolumn.setCellValueFactory(new PropertyValueFactory<>("btn_buy"));
        ObservableList<flight> returnFlights = controller.getReturnFlights();
        ObservableList<flightSearchColumn> data = FXCollections.observableArrayList();
        for (flight f : returnFlights) {
            data.add(new flightSearchColumn(f.getFlightID(),f.getFrom(),f.getDestination(),f.getDepartDate(),f.getReturnDate(),f.getSupplierUserName(),f.getPrice(), new Button(),new Button()));//Tal
        }

        tableResults.setItems(data);

        //tableResults.getColumns().addAll(from,destination,departDate,returnDate,supplier);

    }

    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_returnHome, 600, 400);
    }
}
