package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;

import java.util.Optional;

public class purchaseRequestSubmittedToMeRec extends ARecord {
    public String purchaseRequestID;
    public String flightID;
    public String purchaserUserName;
    public String status;
    public Hyperlink Hyl_flightDetails;
    public Button btn_Accept;
    public Button btn_Reject;


    public purchaseRequestSubmittedToMeRec(String _purchaseRequestID, String _flightID, String _purchaserUserName, String _status, Hyperlink _flightDetails, Button _accept, Button _reject) {
        myController = Controller.getInstance();
        purchaseRequestID = _purchaseRequestID;
        flightID = _flightID;
        purchaserUserName = _purchaserUserName;
        status = _status;
        btn_Accept = _accept;
        if(status.equals("payment in process"))
            btn_Accept.setText("Payment accepted ");
        else if(status.equals("Done"))
            btn_Accept.setDisable(true);
        else btn_Accept.setText("Accept");
        btn_Reject = _reject;
        if(status.equals("payment in process")) {
            btn_Reject.setText("Reject");
            btn_Reject.setDisable(true);
        }
        else btn_Reject.setText("Reject");
        Hyl_flightDetails = _flightDetails;
        Hyl_flightDetails.setText("See Vacation Details");

        //handle press on accept/Payment accepted button.
        btn_Accept.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            if (btn_Accept.getText().equals("Accept")) {
                if (!status.equals("approved") && !status.equals("rejected") && !status.equals("Done")) {
                    Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confAlert.setTitle("Are you sure?");
                    confAlert.setContentText("Are you sure you want to approve this request?");
                    Optional<ButtonType> result = confAlert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        myController.updatePurchaseRequestStatus(purchaseRequestID, "approved");
                        setStatus("approved");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setContentText("Request Approved successfully. :)");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Can not change status");
                    alert.setContentText("Request had already approved or rejected!");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } else {// when pressing Payment accepted
                myController.updatePurchaseRequestStatus(purchaseRequestID, "Done");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Thank you for using EveryVacation4U! :)");
                alert.showAndWait();
                if (!myController.deleteDonePurcahesReq(purchaseRequestID))
                    System.out.println("canot remove from purcahseReq table");//TODO:DELETE!
            }
        });
        //handle press on accept button.
        btn_Reject.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            if (!status.equals("approved") && !status.equals("rejected") && !status.equals("Done")) {
                Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confAlert.setTitle("Are you sure?");
                confAlert.setContentText("Are you sure you want to reject this request?");
                Optional<ButtonType> result = confAlert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    myController.updatePurchaseRequestStatus(purchaseRequestID, "rejected");
                    setStatus("rejected");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Request rejected successfully.  :(");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Can not change status");
                alert.setContentText("Request had already approved or rejected!");
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
                    alert.setTitle("Search returned with 0 results");
                    alert.setContentText("System can not show Vacation details.");
                } else {
                    showVacToBuyDetails(vacationReturned);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
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
