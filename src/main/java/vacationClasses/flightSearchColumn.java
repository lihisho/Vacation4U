package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.InputStream;


public class flightSearchColumn {
    Controller myController;
    public String flightId;//Tal
    public String from;
    public String destination;
    public String departDate;
    public String returnDate;
    public String supplierUserName;
    public String price;
    public Button btnBuy;
    public Button btnViewFlight;

//Tal
    public flightSearchColumn( String _flightID,String _from,String _destination,String _departDate,String _returnDate,String _supplierUserName,String _price, Button viewB, Button buy){
        myController=Controller.getInstance();
        flightId=_flightID;
        from=_from;
        destination=_destination;
        departDate=_departDate;
        returnDate=_returnDate;
        supplierUserName=_supplierUserName;
        price=_price;
        btnBuy=buy;
        btnBuy.setText("buy");
        btnViewFlight=viewB;
        btnViewFlight.setText("more details");

        btnBuy.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setContentText("Purchase order was sent successfully");
            alert.setHeaderText(null);
            alert.showAndWait();
            myController.createPurchaseRequest(flightId,supplierUserName);
        });

        btnViewFlight.setOnAction(event -> {
//TODO: needs to check how we get this detail from the table + change from "3"
            String flightIDForShowingDetails = flightId;
            flight flightReturned= myController.showFlightdeatails(flightIDForShowingDetails);
            if(flightIDForShowingDetails == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search returned with 0 results");
                alert.setContentText("System can not show flight details.");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Flight Details");
                StringBuilder content=new StringBuilder();
                content.append("From: "+ flightReturned.getFrom()+"\n");
                content.append("Destination: "+flightReturned.getDestination()+"\n");
                content.append("Depurture Date: "+flightReturned.getDepartDate()+"\n");
                content.append("Return Date: "+flightReturned.getReturnDate()+"\n");
                content.append("Number of Passengers: "+ flightReturned.getNumOfPassengers()+"\n");
                content.append("Luggage Weight: "+flightReturned.getLuggageWeight()+"\n");
                content.append("Price: "+flightReturned.getPrice()+"\n");
                content.append("Supplier User Name: "+flightReturned.getSupplierUserName());
                alert.setContentText(content.toString());
                alert.setHeaderText(null);
                alert.showAndWait();

                /*FXMLLoader fxmlLoader = new FXMLLoader();
                try {
                    InputStream is = this.getClass().getResource("/showFlightDetailsFromMyPurchese.fxml").openStream();
                    Parent actionScreen = fxmlLoader.load(is);
                    AView showFlightDetailsView = fxmlLoader.getController();
                    showFlightDetailsView.setMyController(this.myController);
                    Scene newScene = new Scene(actionScreen, 600, 400);
                    Stage curStage = (Stage) btnViewFlight.getScene().getWindow();
                    curStage.setScene(newScene);
                    ((showFlightDetailsFromMyPurchaseView)showFlightDetailsView).showFlightDetails(flightReturned);
                    curStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }        });
    }

    public String getFlightId(){
        return flightId;
    }

    public void setFlightId(String s){
        flightId=s;
    }

    public Button getBtnViewFlight(){
        return btnViewFlight;
    }
    public void setBtnViewFlight(Button b){
        btnViewFlight=b;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String s){
        price=s;
    }

    public Button getBtnBuy(){
        return btnBuy;
    }

    public void setBtnBuy(Button b){
        btnBuy=b;
    }


    public String getDestination(){
        return destination;
    }

    public void setDestination(String d){
        destination=d;
    }
    public String getFrom(){
        return from;
    }

    public void setFrom(String d){
        from=d;
    }
    public String getDepartDate(){
        return departDate;
    }

    public void setDepartDate(String d){
        departDate=d;
    }

    public String getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(String d){
        returnDate=d;
    }

    public String getSupplierUserName(){
        return supplierUserName;
    }

    public void setSupplierUserName(String d){
        supplierUserName=d;
    }


}
