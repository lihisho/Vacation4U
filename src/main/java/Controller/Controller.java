package Controller;

import Model.IModel;
import Model.MyModel;
import javafx.collections.ObservableList;
import vacationClasses.User;
import vacationClasses.flight;
import vacationClasses.purchaseRequest;

import java.sql.ResultSet;

/**
 * Class Controller as part of the MVC model.
 * Our controller will move functions from the View to the Model and back from the Model to the View.
 */
public class Controller {
    private static Controller singleton = null;

    //private field to connect the Controller to the Model
    private IModel myModel;
    private static ObservableList<flight>returnFlights;//Tal
    private static ObservableList<purchaseRequest>sellerRequest;//Tal
    private static ObservableList<purchaseRequest>myRequest;//Tal

    private static Controller controller;

    //constructor
    //public Controller(IModel myModel) {
    public Controller() {//Tal

    //this.myModel=myModel;
        //Tal
        this.myModel=MyModel.getInstance();
        }

    //Tal
    public static Controller getInstance() {
        if (singleton == null)
            singleton = new Controller();
        return singleton;

    }

    /**
     * A function that gets the userName and password from the View and sends them to the Model.
     * The function activates the login function un the Model.
     * @param username - the given userName from the view.
     * @param password - the given password from the view.
     * @return true if the login process pass succesfully, else false.
     */
    public boolean login(String username, String password){
        return myModel.login(username,password);
    }

    /**
     * A function that gets all the needed parameters to create new user from the view, sends them to the Model
     * and activates the create_user function in the Model.
     * @param username - the given userName from the view.
     * @param password - the given password from the view.
     * @param firstName - the given firstName from the view.
     * @param lastName - the given lastName from the view.
     * @param birthDate - the given birthDate from the view.
     * @param residence - the given residence from the view.
     * @return true if the creation process passed succesfully, else false.
     */
    public boolean createNewUser(String username,String password, String firstName, String lastName, String birthDate, String residence){
        return  myModel.createUser(username,password,firstName,lastName,birthDate,residence);
    }

    /**
     * A function that gets userName from the view and sends it to the Model.
     * The function activates a function in the model that search user record in the DB by the given userName.
     * @param userName - the given userName from the view
     * @return true if the searching process passed succesfully, else false.
     */
    public boolean searchUserName(String userName){
        return myModel.searchUserName(userName);
    }

    /**
     * A function that activates a function in the model that deletes a record in the DB.
     * @return true if the deletion process passed succesfully, else false.
     */
    public boolean deleteUser(){return myModel.deleteUser();}

    /**
     * A function that gets all the needed parameters to update user details from the view, sends them to the Model
     * and activates the update_user_details function in the Model.
     * @param username - the given userName from the view.
     * @param password - the given password from the view.
     * @param firstName - the given firstName from the view.
     * @param lastName - the given lastName from the view.
     * @param birthDate - the given birthDate from the view.
     * @param residence - the given residence from the view.
     * @return true if the update process passed succesfully, else false.
     */
    public boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence){
        return myModel.updateUser(username,password,firstName,lastName,birthDate,residence);
    }

    /**
     * A function that activates a reading record from the DB (in the Model) by a given userName from the view.
     * @param userName - a given userName from the view. If userName is null, the model will show the details of the current user
     * @return - A UserType that holds all the details to show.
     */
    public User showDetails(String userName){ return myModel.showDetails(null);}

    /**
     *
     * @param userToSearch
     * @return
     */
    public User searchAndReadUser(String userToSearch){ return myModel.searchAndReadUser(userToSearch);}

    public flight showFlightdeatails(String flightID){
        return myModel.showFlightDetails(flightID);
    }

    //Gets the price in order to show in the payment window.
    public String getPriceForPayment(String flightID) {
        return myModel.getPriceForPayment(flightID);
    }
    //Tal
    public ObservableList<flight> searchFlight(String flightDestination, String departDate, String returnDate, int numOfPassengers){
        return myModel.searchFlight(flightDestination,departDate,returnDate,numOfPassengers);
    }

    public boolean createFlight(String from,String destination,String departureD, String returnD, String numOfPass, String luggageWeight,String price) {
        return myModel.createFlight(from,destination,departureD,returnD,numOfPass,luggageWeight,price);
    }

    public void setReturnFlights(ObservableList<flight> returnFlights) {//Tal
        this.returnFlights = returnFlights;
    }

    public ObservableList<flight> getReturnFlights() {//Tal
        return returnFlights;
    }

    public ObservableList<flight> getAllFlights(){
        return myModel.getAllFlights();
    }
    public boolean createPurchaseRequest(String flightId, String supplierUserName) {
        return myModel.createPurchaseRequest(flightId,supplierUserName,"waiting for approval");
    }
    public boolean deleteFlight(String flightID){
        return myModel.deleteFlight(flightID);
    }

    public boolean updatePurchaseRequestStatus(String purchaseRequestID, String status){
        return myModel.updatePurchaseRequestStatus(purchaseRequestID, status);
    }
    public ObservableList<purchaseRequest> getMyRequest() {
        return myModel.getMyRequest();
    }

    public void setMyRequest(ObservableList<purchaseRequest> myRequest) {
        this.myRequest = myRequest;
    }
    public ObservableList<purchaseRequest> gettMyRequest() {
        return myRequest;
    }

    public void setSellerRequest(ObservableList<purchaseRequest> sellerRequest) {
        this.sellerRequest = myRequest;
    }
    public ObservableList<purchaseRequest> gettSellerRequest() {
        return sellerRequest;
    }

    public ObservableList<purchaseRequest> requestForSeller() {
        return myModel.requestForSeller();
    }


}
