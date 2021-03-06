package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import vacationClasses.purchaseRequest;
import vacationClasses.purchaseRequestSubmittedToMeRec;

import java.net.URL;
import java.util.ResourceBundle;


public class purchaseRequestsSubmittedToMeView extends AView implements Initializable{
    private Controller controller = Controller.getInstance();
    public TableView<purchaseRequestSubmittedToMeRec> tableResults;

    public TableColumn<purchaseRequestSubmittedToMeRec, String> requestIDCOl;
    public TableColumn<purchaseRequestSubmittedToMeRec, String> flightIDCol;
    public TableColumn<purchaseRequestSubmittedToMeRec, String> purchaserCol;
    public TableColumn<purchaseRequestSubmittedToMeRec, String> statusCol;
    public TableColumn<purchaseRequestSubmittedToMeRec, String> flightDetCol;
    public TableColumn<purchaseRequestSubmittedToMeRec, String> ActionCol1;
    public TableColumn<purchaseRequestSubmittedToMeRec, String> ActionCol2;
    public Button btn_returnHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        requestIDCOl.setCellValueFactory(new PropertyValueFactory<>("purchaseRequestID"));
        flightIDCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        purchaserCol.setCellValueFactory(new PropertyValueFactory<>("purchaserUserName"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        ActionCol1.setCellValueFactory(new PropertyValueFactory<>("btn_Accept"));
        ActionCol2.setCellValueFactory(new PropertyValueFactory<>("btn_Reject"));
        flightDetCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_flightDetails"));
         Button btn_returnHome;
        // buycolumn.setCellValueFactory(new PropertyValueFactory<>("btn_buy"));
        ObservableList<purchaseRequest> returnPurchase = controller.gettSellerRequest();
        ObservableList<purchaseRequestSubmittedToMeRec> data = FXCollections.observableArrayList();
        for (purchaseRequest p : returnPurchase) {
                data.add(new purchaseRequestSubmittedToMeRec(p.getPurchaseRequestID(),p.getFlightID(),p.getPurchaserUserName(),p.getStatus(),new Hyperlink(),new Button(),new Button()));//Tal
        }

        tableResults.setItems(data);

        //tableResults.getColumns().addAll(from,destination,departDate,returnDate,supplier);

    }

    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_returnHome, 600, 450);
    }
}
