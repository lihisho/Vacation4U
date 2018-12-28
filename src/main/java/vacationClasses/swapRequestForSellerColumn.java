package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;

import java.util.Optional;

public class swapRequestForSellerColumn extends Acolumn {

    public String requestID;
    public String sellerVacationID;
    public Hyperlink Hyl_sellerSideVacationDet;
    public String buyer;
    public String buyerVacationId;
    public Hyperlink Hyl_buyerSideVacationDet;
    public String status;
    public Button btn_Accept;
    public Button btn_Reject;

    //getters
    public String getRequestID(){return requestID;}

    public String getSellerVacationID(){return sellerVacationID;}

    public String getBuyerVacationId(){return buyerVacationId;}

    public Hyperlink getHyl_sellerSideVacationDet(){return Hyl_sellerSideVacationDet;}

    public String getBuyer() {return buyer;}

    public Hyperlink getHyl_buyerSideVacationDet(){return Hyl_buyerSideVacationDet;}

    public String getStatus(){return status;}

    public Button getBtn_Accept() {return btn_Accept; }

    public Button getBtn_Reject(){return btn_Reject;}

    //setters
    public void setRequestID(String requestID){
        this.requestID=requestID;
    }

    public void setSellerVacationID(String selleracationID){
        this.sellerVacationID=selleracationID;
    }

    public void setBuyerVacationId(String buyerVacationId){
        this.buyerVacationId=buyerVacationId;
    }

    public void setHyl_sellerSideVacationDet(Hyperlink hyl_sellerSideVacationDet){
        this.Hyl_sellerSideVacationDet=hyl_sellerSideVacationDet;
    }

    public void setBuyer(String buyer){
        this.buyer=buyer;
    }

    public void setHyl_buyerSideVacationDet(Hyperlink hyl_buyerVacationDet){
        this.Hyl_buyerSideVacationDet=hyl_buyerVacationDet;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public void setBtn_Accept(Button btn_Accept){
        this.btn_Accept=btn_Accept;
    }

    public void setBtn_Reject(Button btn_reject){
        this.btn_Reject=btn_Reject;
    }

    public swapRequestForSellerColumn(String _requestID, String _vacationID, Hyperlink _Hyl_sellerSideVacationDet,
                                      String _buyerVacationId,Hyperlink _Hyl_buyerVacationDet, String _buyer,
                                      String _status,Button _accept,Button _reject ) {
        myController = Controller.getInstance();
        requestID = _requestID;
        sellerVacationID = _vacationID;
        buyer=_buyer;
        buyerVacationId=_buyerVacationId;
        status=_status;
        //hyperLinks
        Hyl_sellerSideVacationDet=_Hyl_sellerSideVacationDet;
        Hyl_sellerSideVacationDet.setText("My vacation");
        Hyl_buyerSideVacationDet=_Hyl_buyerVacationDet;
        Hyl_buyerSideVacationDet.setText("Offered vacation" );
        //buttons
        btn_Accept = _accept;
        btn_Accept.setText("Accept");
        if(status.equals("Done"))
            btn_Accept.setDisable(true);
        btn_Reject = _reject;
        btn_Reject.setText("Reject");


        //handle press on accept button
        btn_Accept.setOnAction(event -> {
            if (btn_Accept.getText().equals("Accept")) {
                if (!status.equals("approved") && !status.equals("rejected") && !status.equals("Done")) {
                    Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confAlert.setTitle("Are you sure?");
                    confAlert.setContentText("Are you sure you want to approve this request?");
                    Optional<ButtonType> result = confAlert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        myController.updateSwapRequestStatus(requestID, "approved");
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
            }
        });

        //handle press on reject button.
        btn_Reject.setOnAction(event -> {
            if (!status.equals("approved") && !status.equals("rejected") && !status.equals("Done")) {
                Alert confAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confAlert.setTitle("Are you sure?");
                confAlert.setContentText("Are you sure you want to reject this request?");
                Optional<ButtonType> result = confAlert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    myController.updateSwapRequestStatus(requestID, "rejected");
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

        // handle press on link to seller vacation details
        Hyl_sellerSideVacationDet.setOnAction(event -> {
            if (!status.equals("Done")) {
                Vacation vacationReturned = myController.showVacationDetails(sellerVacationID);
                if (sellerVacationID == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search returned with 0 results");
                    alert.setContentText("System can not show vacation details.");
                } else {
                    showVacToSwapDetails(vacationReturned);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("Vacation was already swapped, can not show details!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });

        // handle press on link to buyer vacation details
        Hyl_buyerSideVacationDet.setOnAction(event -> {
            if (!status.equals("Done")) {
                Vacation vacationReturned = myController.showVacationDetails(buyerVacationId);
                if (buyerVacationId == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search returned with 0 results");
                    alert.setContentText("System can not show flight details.");
                } else {
                    showVacToSwapDetails(vacationReturned);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("Vacation was already swapped, can not show details!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
    }
}
