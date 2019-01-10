package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class mySwapRequestRec extends ARecord {
    Controller myController;
    public String swapRequestID;
    public String requestedVacationID;
    public String offeredVacationID;
    public String status;
    //Hyperlink
    public Hyperlink Hyl_offeredVacationDet;
    public Hyperlink Hyl_requestedVacationDet;
    //Button
    public Button btn_detailsForPay;

    //getters
    public String getSwapRequestID(){
        return this.swapRequestID;
    }

    public String getRequestedVacationID(){
        return requestedVacationID;
    }

    public String getOfferedVacationID() {
        return offeredVacationID;
    }

    public String getStatus(){
        return status;
    }

    public Hyperlink getHyl_offeredVacationDet() {
        return Hyl_offeredVacationDet;
    }

    public Button getBtn_detailsForPay(){
        return btn_detailsForPay;
    }

    public Hyperlink getHyl_requestedVacationDet(){
        return Hyl_requestedVacationDet;
    }

    //setters
    public void setSwapRequestId(String swapRequestID){
        this.swapRequestID = swapRequestID;
    }

    public void setRequestedVacationID(String requestedVacationID) {
        this.requestedVacationID = requestedVacationID;
    }

    public void setHyl_requestedVacationDet(Hyperlink hyl_requestedVacationDet) {
        Hyl_requestedVacationDet = hyl_requestedVacationDet;
    }

    public void setOfferedVacationID(String offeredVacationID) {
        this.offeredVacationID = offeredVacationID;
    }

    public void setHyl_offeredVacationDet(Hyperlink hyl_offeredVacationDet) {
        Hyl_offeredVacationDet = hyl_offeredVacationDet;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBtn_detailsForPay(Button btn_detailsForPay) {
        this.btn_detailsForPay = btn_detailsForPay;
    }

    @SuppressWarnings("Duplicates")
    public mySwapRequestRec(String _swapRequestID, String _requestedVacationID, String _offeredVacationID,
                            Hyperlink _Hyl_offeredVacationDet, Hyperlink _Hyl_requestedVacationDet, String _buyer,
                            String _status, Button _btn_detailsForPay) {
        myController = Controller.getInstance();
        swapRequestID = _swapRequestID;
        requestedVacationID = _requestedVacationID;
        offeredVacationID=_offeredVacationID;
        status=_status;
        //hyperLinks
        Hyl_offeredVacationDet=_Hyl_offeredVacationDet;
        Hyl_offeredVacationDet.setText("See your vacation details");
        Hyl_requestedVacationDet=_Hyl_requestedVacationDet;
        Hyl_requestedVacationDet.setText("See the requested vacation details" );
        //buttons
        btn_detailsForPay = _btn_detailsForPay;
        btn_detailsForPay.setText("Details for payment");
        btn_detailsForPay.setDisable(true);
        if(status.equals("approved"))
            btn_detailsForPay.setDisable(false );

        //handle press on accept button
        btn_detailsForPay.setOnAction(event ->{
            if (status.equals("approved")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Owner details for connection");
                User seller = myController.getOwnerDetailes(requestedVacationID);
                alert.setContentText("Please contact " + seller.getFirstname() + " " + seller.getLastname() + " to complete payment:\n" + "Email address: " + seller.getEmail() + "\n" + "Phone number: " + seller.getPhoneNumber());
                alert.setHeaderText(null);
                alert.showAndWait();
                myController.updateSwapRequestStatus(swapRequestID, "Done");
                if(!myController.deleteDoneSwapsReq(swapRequestID))
                    System.out.println("record failed to be deleted");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Can not complete payment");
                alert.setContentText("Request must be approved by the supplier!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });

        // handle press on link to seller vacation details
        Hyl_offeredVacationDet.setOnAction(event -> {
            if (!status.equals("Done")) {
                Vacation vacationReturned = myController.showVacationDetails(offeredVacationID);
                if (offeredVacationID == null) {
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
        Hyl_requestedVacationDet.setOnAction(event -> {
            if (!status.equals("Done")) {
                Vacation vacationReturned = myController.showVacationDetails(requestedVacationID);
                if (requestedVacationID == null) {
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
