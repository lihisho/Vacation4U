package vacationClasses;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

public class flight {
    private String flightID;
    private String from;
    private String destination;
    private String departDate;// date is with hour, need to check if prefer string
    private String returnDate;
    private String numOfPassengers;
    private String luggageWeight;
    private String supplierUserName;
    private String price;

    public flight(String flightID,String from, String destination,String departDate,String returnDate, String numOfPassengers, String luggageWeight,String supplier_userName,String price){
        this.flightID=flightID;
        this.from=from;
        this.destination=destination;
        this.departDate=departDate;
        this.returnDate=returnDate;
        this.numOfPassengers=numOfPassengers;
        this.luggageWeight=luggageWeight;
        this.supplierUserName=supplier_userName;
        this.price=price;
    }

    public String getFlightID(){
        return flightID;
    }

    public String getFrom(){
        return from;
    }
    public String getDestination(){
        return destination;
    }
    public String getDepartDate(){
        return departDate;
    }
    public String getReturnDate(){
        return returnDate;
    }
    public String getNumOfPassengers(){
        return numOfPassengers;
    }
    public String getLuggageWeight(){
        return luggageWeight;
    }
    public String getSupplierUserName(){
        return supplierUserName;
    }
    public String getPrice(){
        return price;
    }

    public void setFlightID(String s){
         flightID=s;
    }

    public void setFrom(String s){
         from=s;
    }
    public void setDestination(String s){
         destination=s;
    }
    public void setDepartDate(String s){
         departDate=s;
    }
    public  void setReturnDate(String s){
         returnDate=s;
    }
    public void setNumOfPassengers(String s){
         numOfPassengers=s;
    }
    public void setLuggageWeight(String s){
         luggageWeight=s;
    }
    public final void setSupplierUserName(String s){
        supplierUserName=s;
    }
    public void setPrice(String s){
         price=s;
    }

}
