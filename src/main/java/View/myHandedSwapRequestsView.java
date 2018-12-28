package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vacationClasses.*;

import java.net.URL;
import java.util.ResourceBundle;

public class myHandedSwapRequestsView extends AView implements Initializable {
    private Controller controller = Controller.getInstance();
    public TableView<swapRequestHandedColumn> tableRequestedSwaps;
    public TableColumn<swapRequestHandedColumn, String> requestIDCol;
    public TableColumn<swapRequestHandedColumn, String> requestedVacationIDCol;
    public TableColumn<swapRequestHandedColumn, String> requestedVacationDetCol;
    public TableColumn<swapRequestHandedColumn, String> offeredVacationIDCol;
    public TableColumn<swapRequestHandedColumn, String> offeredVacationDetCol;
    public TableColumn<swapRequestHandedColumn, String> statusCol;
    public TableColumn<swapRequestHandedColumn, String> detailsForPayCol;
    public Button btn_returnHome;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        requestIDCol.setCellValueFactory(new PropertyValueFactory<>("swapRequestID"));
        requestedVacationIDCol.setCellValueFactory(new PropertyValueFactory<>("requestedVacationID"));
        requestedVacationDetCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_requestedVacationDet"));
        offeredVacationIDCol.setCellValueFactory(new PropertyValueFactory<>("offeredVacationID"));
        offeredVacationDetCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_offeredVacationDet"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        detailsForPayCol.setCellValueFactory(new PropertyValueFactory<>("btn_detailsForPay"));

        ObservableList<swapRequest> returnedSwapRequests = controller.getSwapRequestsHandedByBuyer();
        ObservableList<swapRequestHandedColumn> data = FXCollections.observableArrayList();
        for (swapRequest sr : returnedSwapRequests) {
            data.add(new swapRequestHandedColumn(sr.getSwapRequest_id(),sr.getSeller_side_vacation_id(),
                    sr.getBuyer_side_vacation_id(),new Hyperlink(),new Hyperlink(),
                    sr.getBuyer_side_userName(),sr.getSwapStatus(),new Button()));
        }
        tableRequestedSwaps.setItems(data);

    }

    public void backToProfile() {
        openNewWindow("/actionScreen.fxml", btn_returnHome, 600, 450);
    }
}
