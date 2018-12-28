package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;

public class Acolumn {
    Controller myController;

    protected void showVacToSwapDetails(Vacation vacationReturned){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Flight Details");
        StringBuilder content=new StringBuilder();
        if(!vacationReturned.getDepartureFrom().equals("None"))
            content.append("From: "+ vacationReturned.getDepartureFrom()+"\n");
        if(!vacationReturned.getDestination().equals("None"))
            content.append("Destination: "+vacationReturned.getDestination()+"\n");
        if(!vacationReturned.getDepartDate().equals("None"))
            content.append("Depurture Date: "+vacationReturned.getDepartDate()+"\n");
        if(!vacationReturned.getReturnDate().equals("None"))
            content.append("Return Date: "+vacationReturned.getReturnDate()+"\n");
        if(!vacationReturned.getAirline().equals("None"))
            content.append("Flight Airline: "+vacationReturned.getAirline()+"\n");
        if(!vacationReturned.getNumOfPassengers().equals("None"))
            content.append("Number of Passengers: "+ vacationReturned.getNumOfPassengers()+"\n");
        if(!vacationReturned.getLuggageIncluded().equals("None"))
            content.append("Luggage included: "+vacationReturned.getLuggageIncluded()+"\n");
        if(!vacationReturned.getLuggageWeight().equals("None"))
            content.append("Luggage Weight: "+vacationReturned.getLuggageWeight()+"\n");
        if(!vacationReturned.getOriginPrice().equals("None"))
            content.append("Origin Price: "+vacationReturned.getOriginPrice()+"\n");
        if(!vacationReturned.getTicketsType().equals("None"))
            content.append("Ticket Type: "+vacationReturned.getTicketsType()+"\n");
        if(!vacationReturned.getHotel().equals("None"))
            content.append("Hotel: "+vacationReturned.getHotel()+"\n");
        if(!vacationReturned.getHotelRank().equals("None"))
            content.append("Hotel Rank: "+vacationReturned.getHotelRank()+"\n");
        if(!vacationReturned.getVacationType().equals("None"))
            content.append("Vacation Type: "+vacationReturned.getVacationType()+"\n");
        if(!vacationReturned.getSupplierUserName().equals("None"))
            content.append("Supplier User Name: "+vacationReturned.getSupplierUserName());
        alert.setContentText(content.toString());
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    protected void showVacToBuyDetails(Vacation vacationReturned) {
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
        if (!vacationReturned.getHotel().equals("None"))
            content.append("Hotel: " + vacationReturned.getHotel() + "\n");
        if (!vacationReturned.getHotelRank().equals("None"))
            content.append("Hotel Rank: " + vacationReturned.getHotelRank() + "\n");
        if (!vacationReturned.getVacationType().equals("None"))
            content.append("Vacation Type: " + vacationReturned.getVacationType() + "\n");
        if (!vacationReturned.getSupplierUserName().equals("None"))
            content.append("Supplier User Name: " + vacationReturned.getSupplierUserName());
        alert.setContentText(content.toString());
        alert.setHeaderText(null);
    }
}
