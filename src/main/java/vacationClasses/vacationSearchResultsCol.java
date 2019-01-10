package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.InputStream;


public class vacationSearchResultsCol extends Acolumn{
    public String flightId;//Tal
    public String from;
    public String destination;
    public String departDate;
    public String returnDate;
    public String supplierUserName;
    public String price;
    public Button btnBuy;
    private Hyperlink Hyl_ViewFlight; //TODO: lihi changed to hyperlink


    public vacationSearchResultsCol(String _flightID, String _from, String _destination, String _departDate, String _returnDate, String _supplierUserName, String _price, Hyperlink viewB, Button buy){
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
        Hyl_ViewFlight=viewB;
        Hyl_ViewFlight.setText("vacation details");

        btnBuy.setOnAction(event -> {//TODO: add the function insert to DB and lists of users the request
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setContentText("Purchase order was sent successfully");
            alert.setHeaderText(null);
            alert.showAndWait();
            myController.createPurchaseRequest(flightId,supplierUserName);
        });

        Hyl_ViewFlight.setOnAction(event -> {
//TODO: needs to check how we get this detail from the table + change from "3"
            String flightIDForShowingDetails = flightId;
            Vacation vacationReturned= myController.showVacationDetails(flightIDForShowingDetails);
            if(flightIDForShowingDetails == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search returned with 0 results");
                alert.setContentText("System can not show flight details.");
            }
            else {
                showVacToBuyDetails(vacationReturned);

            }        });
    }

    public String getFlightId(){
        return flightId;
    }

    public void setFlightId(String s){
        flightId=s;
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

    public Hyperlink getHyl_ViewFlight(){
        return Hyl_ViewFlight;
    }

    public void setHyl_ViewFlight(Hyperlink d){
        Hyl_ViewFlight=d;
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

    public void showVacToBuyDetails(Vacation vacationReturned) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Flight Details");
        StringBuilder content = new StringBuilder();
        if (!vacationReturned.getDepartureFrom().equals("None"))
            content.append("From: " + vacationReturned.getDepartureFrom() + "\n");
        if (!vacationReturned.getDestination().equals("None"))
            content.append("Destination: " + vacationReturned.getDestination() + "\n");
        if (!vacationReturned.getDepartDate().equals("None"))
            content.append("Depurture Date: " + vacationReturned.getDepartDate() + "\n");
        if (!vacationReturned.getReturnDate().equals("None"))
            content.append("Return Date: " + vacationReturned.getReturnDate() + "\n");
        if (!vacationReturned.getAirline().equals("None"))
            content.append("Flight Airline: " + vacationReturned.getAirline() + "\n");
        if (!vacationReturned.getNumOfPassengers().equals("None"))
            content.append("Number of Passengers: " + vacationReturned.getNumOfPassengers() + "\n");
        if (!vacationReturned.getLuggageIncluded().equals("None"))
            content.append("Luggage included: " + vacationReturned.getLuggageIncluded() + "\n");
        if (!vacationReturned.getLuggageWeight().equals("None"))
            content.append("Luggage Weight: " + vacationReturned.getLuggageWeight() + "\n");
        if (!vacationReturned.getOriginPrice().equals("None"))
            content.append("Origin Price: " + vacationReturned.getOriginPrice() + "\n");
        if (!vacationReturned.getPriceOffered().equals("-1"))
            content.append("Offered Price: " + vacationReturned.getOriginPrice() + "\n");
        if (!vacationReturned.getTicketsType().equals("None"))
            content.append("Ticket Type: " + vacationReturned.getTicketsType() + "\n");
        if (!vacationReturned.getHotel().equals("None")) content.append("Hotel: " + vacationReturned.getHotel() + "\n");
        if (!vacationReturned.getHotelRank().equals("None"))
            content.append("Hotel Rank: " + vacationReturned.getHotelRank() + "\n");
        if (!vacationReturned.getVacationType().equals("None"))
            content.append("Vacation Type: " + vacationReturned.getVacationType() + "\n");
        if (!vacationReturned.getSupplierUserName().equals("None"))
            content.append("Supplier User Name: " + vacationReturned.getSupplierUserName());
        alert.setContentText(content.toString());
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
