package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class paymentView extends AView {
    //Buttons and text fields:
    public TextField txtfld_firstName;
    public TextField txtfld_lastName;
    public TextField txtfld_cardNumber;
    public TextField txtfld_usersID;
    public TextField txtfld_CVV;
    public ChoiceBox monthChoose;
    public ChoiceBox yearChoose;
    public Button btn_pay;
    public Button btn_cancel;
    public Label priceFromDB;

    private String purchaseRequestID;
    private String flightID;

    //Get the price from database.
    public void set_price(String price){
        priceFromDB.setText(price);
    }
    //Set months list 1-12.
    public void set_months(){
        ObservableList<String> months= FXCollections.observableArrayList();

        for(int i=1;i<13;i++) {
            months.add(""+i);
        }
        monthChoose.setItems(months);
    }
    //Set a list of years(the next 5 years).
    public void set_years(){
        ObservableList<String> years= FXCollections.observableArrayList();
        for(int i=2018;i<5;i++) {
            years.add(""+i);
        }
        yearChoose.setItems(years);
    }
// Done button
    public void handlePayButton (){
        displayInformationMessage("payment completed successfully", "Payment confirmation");
        myController.deleteFlight(flightID);
        myController.updatePurchaseRequestStatus(purchaseRequestID, "Done");
        openNewWindow("actionScreen.fxml", btn_pay, 400,400);
    }
    //Cancel button.
    public void handleCancelButton(){
        openNewWindow("actionScreen.fxml", btn_cancel, 400,400);
    }

    public String getPurchaseRequestID() {
        return purchaseRequestID;
    }

    public void setPurchaseRequestID(String purchaseRequestID) {
        this.purchaseRequestID = purchaseRequestID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getFlightID() {
        return flightID;
    }
}
