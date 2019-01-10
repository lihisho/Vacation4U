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
import vacationClasses.swapRequest;
import vacationClasses.swapRequestSubmittedToMeRec;

import java.net.URL;
import java.util.ResourceBundle;

public class swapRequestSubmittedToMeView extends AView implements Initializable {

    private Controller controller = Controller.getInstance();
    public TableView<swapRequestSubmittedToMeRec> tableSwapResults;
    public TableColumn<swapRequestSubmittedToMeRec, String> requestIDCOl;
    public TableColumn<swapRequestSubmittedToMeRec, String> sellerVacationIDCol;
    public TableColumn<swapRequestSubmittedToMeRec, String> sellerSideVacationDetCol;
    public TableColumn<swapRequestSubmittedToMeRec, String> buyerCol;
    public TableColumn<swapRequestSubmittedToMeRec, String> buyerVacationIDCol;
    public TableColumn<swapRequestSubmittedToMeRec, String> buyerSideVacationDetCol;
    public TableColumn<swapRequestSubmittedToMeRec, String> statusCol;
    public TableColumn<swapRequestSubmittedToMeRec, String> ActionCol1;
    public TableColumn<swapRequestSubmittedToMeRec, String> ActionCol2;
    public Button btn_back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        requestIDCOl.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        sellerVacationIDCol.setCellValueFactory(new PropertyValueFactory<>("sellerVacationID"));
        sellerSideVacationDetCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_sellerSideVacationDet"));
        buyerCol.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        buyerVacationIDCol.setCellValueFactory(new PropertyValueFactory<>("buyerVacationId"));
        buyerSideVacationDetCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_buyerSideVacationDet"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        ActionCol1.setCellValueFactory(new PropertyValueFactory<>("btn_Accept"));
        ActionCol2.setCellValueFactory(new PropertyValueFactory<>("btn_Reject"));
        //Button btn_returnHome;

        ObservableList<swapRequest> returnPurchase = controller.getSwapRequestsForSeller();
        ObservableList<swapRequestSubmittedToMeRec> data = FXCollections.observableArrayList();
        for (swapRequest sr : returnPurchase) {
            data.add(new swapRequestSubmittedToMeRec(sr.getSwapRequest_id(),sr.getSeller_side_vacation_id(),new Hyperlink(),
                    sr.getBuyer_side_vacation_id(),new Hyperlink(), sr.getBuyer_side_userName(),sr.getSwapStatus(),new Button(),new Button()));
        }
        tableSwapResults.setItems(data);

    }
    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_back, 600, 400);
    }
}
