package View;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import vacationClasses.*;

import java.awt.*;

public class showFlightDetailsView extends AView{

    public TextField txtfld_destination;
    public TextField txtfld_depurtureDate;
    public TextField txtfld_returnDate;
    public TextField txtfld_numOfPass;
    public TextField txtfld_luggageWeight;
    public TextField txtfld_price;
    public TextField txtfld_supplierName;
    public Button btn_returnToMyPurchaseRequests;

    public void showFlightDetails(flight flight){
        txtfld_destination.setText(flight.getDestination());
        txtfld_depurtureDate.setText(flight.getDepartDate());
        txtfld_returnDate.setText(flight.getReturnDate());
        txtfld_numOfPass.setText(flight.getNumOfPassengers());
        txtfld_luggageWeight.setText(flight.getLuggageWeight());
        txtfld_price.setText(flight.getPrice());
        txtfld_supplierName.setText(flight.getSupplierUserName());
    }

    public void loadMyPurchaseRequestsScreen() {
        openNewWindow("/myPurchaseRequests.fxml", btn_returnToMyPurchaseRequests, 500, 400);
    }

}
