package Controller;

import Model.IModel;
import Model.MyModel;
import javafx.collections.ObservableList;
import vacationClasses.User;
import vacationClasses.Vacation;
import vacationClasses.purchaseRequest;
import vacationClasses.swapRequest;

/**
 * Class Controller as part of the MVC model.
 * Our controller will move functions from the View to the Model and back from the Model to the View.
 */
public class Controller {
    private static Controller singleton = null;

    //private field to connect the Controller to the Model
    private IModel myModel;
    private static ObservableList<Vacation> returnVacations;//Tal
    private static ObservableList<purchaseRequest>sellerRequest;//Tal
    private static ObservableList<purchaseRequest>myRequest;
    private static ObservableList<swapRequest> sellerSwapRequests;
    private static ObservableList<swapRequest> mySwapRequests;
    private static ObservableList<Vacation>returnedMyVacations;
    private static String vacationToChange;

    private static Controller controller;

    //constructor
    //public Controller(IModel myModel) {
    public Controller() {
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
    public boolean createNewUser(String username,String password, String firstName, String lastName, String birthDate, String residence,String email,String phoneNum){
        return  myModel.createUser(username,password,firstName,lastName,birthDate,residence,email,phoneNum);
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
    public boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence,String phoneNum,String email){
        return myModel.updateUser(username,password,firstName,lastName,birthDate,residence,phoneNum,email);
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
    public User searchAndReadUser(String userToSearch){
        return myModel.searchAndReadUser(userToSearch);
    }

    public Vacation showVacationDetails(String vacationID){
        return myModel.showVacationDetails(vacationID);
    }

    //Gets the price in order to show in the payment window.
    public String getPriceForPayment(String flightID) {
        return myModel.getPriceForPayment(flightID);
    }

    public boolean createVacation(String departureFrom, String destination, String departureD, String returnD,
                                  String airline, String luggageIncluded, String luggageWeight, String numOfPass,
                                  String ticketsType, String hotel, String hotelRank, String vacationType,
                                  String originPrice, String priceOffered) {
        return myModel.createVacation(departureFrom,destination,departureD,returnD, airline,
                luggageIncluded,luggageWeight,numOfPass,ticketsType, hotel, hotelRank, vacationType,
                originPrice, priceOffered);
    }

    public void setReturnVacations(ObservableList<Vacation> returnVacations) {
        this.returnVacations = returnVacations;
    }

    public ObservableList<Vacation> getReturnVacations() {
        return returnVacations;
    }

    public ObservableList<Vacation> getAllVacations(){
        return myModel.getAllVacations();
    }
    public boolean createPurchaseRequest(String flightId, String supplierUserName) {
        return myModel.createPurchaseRequest(flightId,supplierUserName,"waiting for approval");
    }

    public boolean createSwapRequest(String vacationID, String supplierUserName,String vacationIDForSwap){
        return myModel.createSwapRequest(vacationID,supplierUserName,vacationIDForSwap,"waiting for approval");
    }

    public boolean deleteVacation(String flightID){
        return myModel.deleteVacation(flightID);
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
        this.sellerRequest = sellerRequest;
    }

    public void setMySwapRequests(ObservableList<swapRequest> swapRequests){
        mySwapRequests=swapRequests;
    }
    public void setSellerSwapRequests(ObservableList<swapRequest> sellerSwapRequests ){
        this.sellerSwapRequests=sellerSwapRequests;
    }
    public ObservableList<purchaseRequest> gettSellerRequest() {
        return sellerRequest;
    }

    public ObservableList<swapRequest> getSwapRequestsForSeller() {
        return sellerSwapRequests;
    }



    public ObservableList<purchaseRequest> requestForSeller() {
        return myModel.requestForSeller();
    }
    //TODO: check uses?
    public User getOwnerDetailes(String vacationID) {
        return myModel.getOwnerDetailes(vacationID);
    }

    public boolean deleteDonePurcahesReq(String purchaseRequestID) {
        return myModel.deleteDonePurcahesReq(purchaseRequestID);
    }

    public String getCurrUserName() {
        return myModel.getCurrUserName();
    }

    public boolean searchEmail(String email) {
        return myModel.searchEmail(email);
    }

    public ObservableList<Vacation> getMyVacationToSwitch() {
        return myModel.getMyVacationToSwitch();
    }

    public void setReturnedMyVacations(ObservableList<Vacation> returnedMyVacations) {
        this.returnedMyVacations = returnedMyVacations;
    }

    public ObservableList<Vacation> getThisMyVacationToSwitch(){
        return this.returnedMyVacations;
    }

    public void setVacationToSwap(String vacationToChange) {
        this.vacationToChange = vacationToChange;
    }
    public String getVacationToSwap() {
        return this.vacationToChange;
    }

    public ObservableList<Vacation> searchVacationToBuy(String vacationDestination, String departDate, String returnDate, String numOfPassengers) {
        return  myModel.searchVacationToBuy(vacationDestination,departDate ,returnDate, numOfPassengers);
    }

    public ObservableList<Vacation> searchVacationToSwap(String vacationDestination, String departDate, String returnDate, String numOfPassengers) {
        return  myModel.searchVacationToSwap(vacationDestination,departDate ,returnDate, numOfPassengers);
    }
    public boolean updateSwapRequestStatus(String requestID, String status) {
        return myModel.updateSwapRequestStatus(requestID,status);
    }

    public ObservableList<swapRequest> swapRequestsForSeller() {
        return myModel.swapRequestsForSeller();
    }

    public boolean deleteDoneSwapsReq(String swapRequestID) {
        return myModel.deleteDoneSwapsReq(swapRequestID);
    }

    public ObservableList<swapRequest> getSwapRequestsHandedByBuyer() {
        return mySwapRequests;
    }

    public ObservableList<swapRequest> myHandedSwapRequests() {
        return myModel.myHandedSwapRequests();
    }
}
