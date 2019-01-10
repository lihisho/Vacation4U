package vacationClasses;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
enum designation {toSwap, forSale}
public class Vacation {

    //private fields

    private String vacationID;
    private String departureFrom;
    private String destination;
    private String departDate;
    private String returnDate;
    private String airline;
    private String luggageIncluded;
    private String luggageWeight;
    private String numOfPassengers;
    private String ticketsType;
    private String hotel;
    private String hotelRank;
    private String vacationType;
    private String originPrice;
    private String priceOffered;
    private String supplierUserName;
    private designation vacationDesignatation;


    //constructor
    public Vacation(String vacationID, String departureFrom, String destination, String departDate, String returnDate,
                    String airline, String luggageIncluded, String luggageWeight, String numOfPassengers,
                    String ticketsType, String hotel, String hotelRank, String vacationType, String originPrice,
                    String priceOffered, String supplier_userName){
        this.vacationID=vacationID;
        this.departureFrom=departureFrom;
        this.destination=destination;
        this.departDate=departDate;
        this.returnDate=returnDate;
        this.airline = airline;
        this.luggageIncluded = luggageIncluded;
        this.luggageWeight=luggageWeight;
        this.numOfPassengers=numOfPassengers;
        this.ticketsType=ticketsType;
        this.hotel=hotel;
        this.hotelRank=hotelRank;
        this.vacationType=vacationType;
        this.originPrice=originPrice;
        this.priceOffered=priceOffered;
        this.supplierUserName=supplier_userName;
        if(priceOffered.equals("-1"))
            vacationDesignatation = designation.toSwap;
        else
            vacationDesignatation = designation.forSale;

    }

    //setters and getters

    public String getVacationID(){
        return vacationID;
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

    public void setVacationIDID(String s){
        vacationID=s;
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

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getLuggageIncluded() {
        return luggageIncluded;
    }

    public void setLuggageIncluded(String luggageIncluded) {
        this.luggageIncluded = luggageIncluded;
    }

    public String getTicketsType() {
        return ticketsType;
    }

    public void setTicketsType(String ticketsType) {
        this.ticketsType = ticketsType;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getHotelRank() {
        return hotelRank;
    }

    public void setHotelRank(String hotelRank) {
        this.hotelRank = hotelRank;
    }

    public String getVacationType() {
        return vacationType;
    }

    public void setVacationType(String vacationType) {
        this.vacationType = vacationType;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getPriceOffered() {
        return priceOffered;
    }

    public void setPriceOffered(String priceOffered) {
        this.priceOffered = priceOffered;
    }
}
