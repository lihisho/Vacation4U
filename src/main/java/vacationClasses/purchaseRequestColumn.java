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

public class purchaseRequestColumn {
    Controller myController;
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
        btn_pay.setText("Pay Owner");
        Hyl_flightDetails = _flightDetails;
        Hyl_flightDetails.setText("See flight Details");

        //handle press on pay button.
        btn_pay.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            if (status.equals("approved")) {
                String price = myController.getPriceForPayment(flightID); // TODO:get from DB
                FXMLLoader fxmlLoader = new FXMLLoader();
                try {
                    InputStream is = this.getClass().getResource("/payment.fxml").openStream();
                    Parent parent = fxmlLoader.load(is);
                    paymentView payView = fxmlLoader.getController();
                    payView.setMyController(this.myController);
                    Scene newScene = new Scene(parent, 400, 500);
                    Stage curStage = (Stage) btn_pay.getScene().getWindow();
                    curStage.setScene(newScene);
                    payView.setPurchaseRequestID(purchaseRequestID);
                    payView.setFlightID(flightID);
                    payView.set_price(price);
                    payView.set_months();
                    payView.set_years();
                    curStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText("Flight was already sold, can not show details!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }


            //TODO: add tals code: show details.
        });

    }

}
