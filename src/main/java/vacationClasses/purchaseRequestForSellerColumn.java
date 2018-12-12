package vacationClasses;

import Controller.Controller;
import View.paymentView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.InputStream;

public class purchaseRequestForSellerColumn {
    Controller myController;
    public String purchaseRequestID;
    public String flightID;
    public String purchaserUserName;
    public String status;
    public Hyperlink Hyl_flightDetails;
    public Button btn_Accept;
    public Button btn_Reject;


    public purchaseRequestForSellerColumn(String _purchaseRequestID, String _flightID, String _purchaserUserName, String _status, Hyperlink _flightDetails, Button _accept, Button _reject) {
        myController = Controller.getInstance();
        purchaseRequestID = _purchaseRequestID;
        flightID = _flightID;
        purchaserUserName = _purchaserUserName;
        status = _status;
        btn_Accept = _accept;
        btn_Accept.setText("Accept");
        btn_Reject = _reject;
        btn_Reject.setText("Reject");
        Hyl_flightDetails = _flightDetails;
        Hyl_flightDetails.setText("See flight Details");

        //handle press on accept button.
        btn_Accept.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            if (!status.equals("approved") || !status.equals("rejected")) {
                myController.updatePurchaseRequestStatus(purchaseRequestID, "approved");
                setStatus("approved");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Request Approved successfully. :)");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("can not change status. already approved or rejected!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        //handle press on accept button.
        btn_Reject.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            if (!status.equals("approved") || !status.equals("rejected")) {
                myController.updatePurchaseRequestStatus(purchaseRequestID, "rejected");
                setStatus("rejected");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Request rejected successfully.  :(");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("can not change status. already approved or rejected!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        // handle press on link to details.
        Hyl_flightDetails.setOnAction(event -> {
            if (!status.equals("Done")) { //TODO: check name of status.
                flight flightReturned = myController.showFlightdeatails(flightID);
                if (flightID == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search returned with 0 results");
                    alert.setContentText("System can not show flight details.");
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Flight Details");
                    StringBuilder content = new StringBuilder();
                    content.append("From:" + flightReturned.getFrom() + "\n");
                    content.append("Destination:" + flightReturned.getDestination() + "\n");
                    content.append("Depurture Date:" + flightReturned.getDepartDate() + "\n");
                    content.append("Return Date:" + flightReturned.getReturnDate() + "\n");
                    content.append("Number of Passengers:" + flightReturned.getNumOfPassengers() + "\n");
                    content.append("Luggage Weight:" + flightReturned.getLuggageWeight() + "\n");
                    content.append("Price:" + flightReturned.getPrice() + "\n");
                    content.append("Supplier User Name:" + flightReturned.getSupplierUserName());
                    alert.setContentText(content.toString());
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("Flight was already sold, can not show details!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }


            //TODO: add tals code: show details.
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

    public String getPurchaserUserName() {
        return purchaserUserName;
    }

    public void setPurchaserUserName(String purchaserUserName) {
        this.purchaserUserName = purchaserUserName;
    }

    public Hyperlink getHyl_flightDetails() {
        return Hyl_flightDetails;
    }

    public void setHyl_flightDetails(Hyperlink Hyl_flightDetails) {
        this.Hyl_flightDetails = Hyl_flightDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Button getBtn_Accept() {
        return btn_Accept;
    }

    public void setBtn_Accept(Button btn_Accept) {
        this.btn_Accept = btn_Accept;
    }

    public Button getBtn_Reject() {
        return btn_Reject;
    }

    public void setBtn_Reject(Button btn_Reject) {
        this.btn_Reject = btn_Reject;
    }
}
