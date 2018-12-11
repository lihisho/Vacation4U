package Model;

import javafx.collections.ObservableList;
import vacationClasses.User;
import vacationClasses.flight;
import vacationClasses.purchaseRequest;

import java.sql.ResultSet;

public interface IModel {
    // create
    boolean createUser(String username,String password, String birthDate, String firstName, String lastName, String residence);
    // read
    boolean searchUserName(String username0);
    // update
    boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence);
    //delete
    boolean deleteUser();

    User showDetails(String userName);

    // login
    boolean login(String username, String password);

    User searchAndReadUser(String userName);

    String getPriceForPayment(String flightID);
    ObservableList<flight> searchFlight(String flightDestination, String departDate, String returnDate, int numOfPassengers);

    boolean createFlight(String from,String destination, String departureD, String returnD, String numOfPass, String luggageWeight,String price);

    flight showFlightDetails(String flightID);

    ObservableList<flight> getAllFlights();

    boolean createPurchaseRequest(String flightId, String supplierUserName, String waiting_for_approval);

    boolean updatePurchaseRequestStatus(String purchaseRequestID, String status);

    boolean deleteFlight(String flightID);
    ObservableList<purchaseRequest> getMyRequest();

    ObservableList<purchaseRequest> requestForSeller();

}
