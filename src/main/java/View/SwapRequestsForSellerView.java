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
import vacationClasses.swapRequestForSellerColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class SwapRequestsForSellerView extends AView implements Initializable {

    private Controller controller = Controller.getInstance();
    public TableView<swapRequestForSellerColumn> tableSwapResults;
    public TableColumn<swapRequestForSellerColumn, String> requestIDCOl;
    public TableColumn<swapRequestForSellerColumn, String> sellerVacationIDCol;
    public TableColumn<swapRequestForSellerColumn, String> sellerSideVacationDetCol;
    public TableColumn<swapRequestForSellerColumn, String> buyerCol;
    public TableColumn<swapRequestForSellerColumn, String> buyerVacationIDCol;
    public TableColumn<swapRequestForSellerColumn, String> buyerSideVacationDetCol;
    public TableColumn<swapRequestForSellerColumn, String> statusCol;
    public TableColumn<swapRequestForSellerColumn, String> ActionCol1;
    public TableColumn<swapRequestForSellerColumn, String> ActionCol2;
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
        ObservableList<swapRequestForSellerColumn> data = FXCollections.observableArrayList();
        for (swapRequest sr : returnPurchase) {
            data.add(new swapRequestForSellerColumn(sr.getSwapRequest_id(),sr.getSeller_side_vacation_id(),new Hyperlink(),
                    sr.getBuyer_side_vacation_id(),new Hyperlink(), sr.getBuyer_side_userName(),sr.getSwapStatus(),new Button(),new Button()));
        }
        tableSwapResults.setItems(data);

    }
    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_back, 600, 400);
    }
}
