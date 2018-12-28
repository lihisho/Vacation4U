package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class purchaseRequestColumn extends Acolumn {
    public String purchaseRequestID;
    public String flightID;
    public String status;
    public Button btn_pay;
    public Hyperlink Hyl_flightDetails;

    public purchaseRequestColumn(String _orderID, String _flightID, String _status, Hyperlink _flightDetails, Button pay) {
        myController = Controller.getInstance();
        purchaseRequestID = _orderID;
        flightID = _flightID;
        status = _status;
        btn_pay = pay;
        btn_pay.setText("Show owner details for payment");
        if (status.equals( "waiting for approval"))
            btn_pay.setDisable(true);
        Hyl_flightDetails = _flightDetails;
        Hyl_flightDetails.setText("See vacation Details");

        //handle press on pay button.
        btn_pay.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            if (status.equals("approved") || status.equals("payment in process")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Owner details for payment");
                User seller = myController.getOwnerDetailes(flightID);
                alert.setContentText("Please contact " + seller.getFirstname() + " " + seller.getLastname() + " to complete payment:\n" + "Email address: " + seller.getEmail() + "\n" + "Phone number: " + seller.getPhoneNumber());
                alert.setHeaderText(null);
                alert.showAndWait();
                myController.updatePurchaseRequestStatus(purchaseRequestID, "payment in process");

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Can not complete payment");
                alert.setContentText("Request must be approved by the supplier!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        // handle press on link to details.
        Hyl_flightDetails.setOnAction(event -> {
            if (!status.equals("Done")) { //TODO: check name of status.
                Vacation vacationReturned = myController.showVacationDetails(flightID);
                if (flightID == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System can not show vacation details");
                    alert.setContentText("Search returned with 0 results.");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else if(vacationReturned == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("System can not show Vacation details");
                    alert.setContentText("Flight was already sold, can not show details!");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
                else{
                    showVacToBuyDetails(vacationReturned);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System can not show Vacation details");
                alert.setContentText("Flight was already sold, can not show details!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }

        });

    }
    public String getPurchaseRequestID() {
        return purchaseRequestID;
    }

    public void setPurchaseRequestID(String purchaseRequestID) {
        this.purchaseRequestID = purchaseRequestID;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Button getBtn_pay() {
        return btn_pay;
    }

    public void setBtn_pay(Button btn_pay) {
        this.btn_pay = btn_pay;
    }

    public Hyperlink getHyl_flightDetails() {
        return Hyl_flightDetails;
    }

    public void setHyl_flightDetails(Hyperlink hyl_flightDetails) {
        Hyl_flightDetails = hyl_flightDetails;
    }


}
