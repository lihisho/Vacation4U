package Model;

import javafx.collections.ObservableList;
import vacationClasses.User;
import vacationClasses.Vacation;

import vacationClasses.purchaseRequest;
import vacationClasses.swapRequest;

import java.sql.ResultSet;

public interface IModel {
    // create
    boolean createUser(String username,String password, String birthDate, String firstName, String lastName, String residence,String email,String phoneNum);
    // read
    boolean searchUserName(String username0);
    // update
    boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence,String phoneNum, String email);
    //delete
    boolean deleteUser();

    User showDetails(String userName);

    // login
    boolean login(String username, String password);

    User searchAndReadUser(String userName);

    String getPriceForPayment(String flightID);
    ObservableList<Vacation> searchVacationToBuy(String flightDestination, String departDate, String returnDate, String numOfPassengers);

    boolean createVacation(String departureFrom, String destination, String departureD, String returnD,
                           String airline, String luggageIncluded, String luggageWeight, String numOfPass,
                           String ticketsType, String hotel, String hotelRank, String vacationType, String originPrice,
                           String priceOffered);

    Vacation showVacationDetails(String flightID);

    ObservableList<Vacation> getAllVacations();

    boolean createPurchaseRequest(String flightId, String supplierUserName, String waiting_for_approval);

    boolean createSwapRequest(String vacationID, String supplierUserName,String vacationIDForSwap, String status);

    boolean updatePurchaseRequestStatus(String purchaseRequestID, String status);

    boolean deleteVacation(String flightID);
    ObservableList<purchaseRequest> getMyRequest();

    ObservableList<purchaseRequest> requestForSeller();

    String getCurrUserName();

    User getOwnerDetailes(String flightID);

    boolean deleteDonePurcahesReq(String purchaseRequestID);

    boolean searchEmail(String email);

    ObservableList<Vacation> getMyVacationToSwitch();

    ObservableList<Vacation> searchVacationToSwap(String vacationDestination, String departDate, String returnDate, String numOfPassengers);
    ObservableList<swapRequest> swapRequestsForSeller();

    boolean updateSwapRequestStatus(String requestID, String status);

    boolean deleteDoneSwapsReq(String swapRequestID);

    ObservableList<swapRequest> myHandedSwapRequests();
}
