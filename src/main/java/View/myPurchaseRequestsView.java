package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vacationClasses.*;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class myPurchaseRequestsView extends AView implements Initializable{

    private Controller controller = Controller.getInstance();

    public TableView<purchaseRequestColumn> tableResults;

    public TableColumn<flightSearchColumn, String> requestIDCOl;
    public TableColumn<flightSearchColumn, String> flightIDCol;
    public TableColumn<flightSearchColumn, String> statusCol;
    public TableColumn<flightSearchColumn, Hyperlink> flightDetCol;
    public TableColumn<flightSearchColumn, Button> payCol;

    public Button btn_returnHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        requestIDCOl.setCellValueFactory(new PropertyValueFactory<>("purchaseRequestID"));

        flightIDCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));

        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        payCol.setCellValueFactory(new PropertyValueFactory<>("btn_pay"));

        flightDetCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_flightDetails"));



        // buycolumn.setCellValueFactory(new PropertyValueFactory<>("btn_buy"));
        ObservableList<purchaseRequest> returnPurchase = controller.gettMyRequest();
        ObservableList<purchaseRequestColumn> data = FXCollections.observableArrayList();
        for (purchaseRequest p : returnPurchase) {

                data.add(new purchaseRequestColumn(p.getPurchaseRequestID(),p.getFlightID(),p.getStatus(),new Hyperlink(),new Button()));//Tal
        }

        tableResults.setItems(data);

        //tableResults.getColumns().addAll(from,destination,departDate,returnDate,supplier);

    }

    public void backToProfile() {
        openNewWindow("/actionScreen.fxml", btn_returnHome, 600, 400);
    }
}
