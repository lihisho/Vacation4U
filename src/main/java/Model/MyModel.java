package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vacationClasses.User;
import vacationClasses.flight;
import vacationClasses.purchaseRequest;

import java.sql.*;

/**
 * A Model class as part of the MVC model.
 * Our Model holds the DB of the system and all the actions on the DB are in this class
 */
public class MyModel implements IModel {
    private static IModel singleton = null;

    //a field that describes the connection to the DB
    private Connection conn = null;
    //the current userName which is now connected to the system.
    private String currentUserName = null;
    //the current user which is now connected to the system.
    private User currUser;

    private int flightCounter;
    //Yafit
    //private int purchaseRequestCounter;

    //constructor
    public MyModel() {
        //connect the DB
        this.connect();
        //create user table in the DB, if doesn't exist
        this.createUsersTable();
        this.createFlightTable();//Tal//create flight table in the DB
        this.createPurchaseRequestTable();//Yafit
        flightCounter=1;
        //purchaseRequestCounter = 1;
    }
    //Tal
    public static IModel getInstance() {
        if (singleton == null)
            singleton = new MyModel();
        return singleton;

    }

    /**
     * A function that connect to the Database
     */
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:Vacation4UDatabase.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            // System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A function that closes the connection to the DB
     */
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * The login function checks whether the username exits in the database. If so, checks whether the password is
     * identical to the password which is associated to the user in the DB.
     *
     * @param username - the username that wish to log in to the system.
     * @param password - the username's password.
     * @return true if the user logged successfully.
     */
    public boolean login(String username, String password) {
        //search if the given username exists in the database
        if(!searchUserName(username))
            return false;
        //check whether the password is correct
        String sql = "SELECT password FROM users WHERE user_name = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, username);
            ResultSet rs = pstm.executeQuery();
            rs.next();//makes the first row the current row.
            if (rs.getString(1).equals(password))
            //password is correct!
            {
                //update the current logged user.
                this.currentUserName = username;
                return true;
            }
            return false;
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Create a new table in the test database
     */
    public void createUsersTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	user_name TEXT PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	password text NOT NULL,\n"
                + "	first_name TEXT NOT NULL,\n"
                + " last_name TEXT NOT NULL,\n"
                + " birth_date TEXT NOT NULL,\n"
                + " residence TEXT NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //Tal
    /**
     * Create a new table in the test database
     */
    public void createFlightTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS flights (\n"
                + "	flight_id TEXT PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	fly_from text NOT NULL,\n"
                + "	destination text NOT NULL,\n"
                + "	depart_Date TEXT NOT NULL,\n"
                + " return_Date TEXT NOT NULL,\n"
                + " num_of_passengers TEXT NOT NULL,\n"
                + " luggage_weight TEXT NOT NULL,\n"
                + " price TEXT NOT NULL NOT NULL,\n"
                + " supplier_username TEXT NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Yafit
    /**
     * Create a new table in the test database
     */
    public void createPurchaseRequestTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS purchaseRequests (\n"
                + "	purchaseRequest_id TEXT PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	flight_id TEXT NOT NULL,\n"
                + "	supplier_userName TEXT NOT NULL,\n"
                + " purchaser_userName TEXT NOT NULL,\n"
                + " status TEXT NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A function that search according to unique userName
     *
     * @param userName - a given userName to search by from the controller
     * @return true if the search process passed succesfully, else false
     */
    public boolean searchUserName(String userName) {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, userName);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) return true;
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        //disconnect
    }

    /**
     * A function that creates a new record  in the user table in the DB.
     *
     * @param username  - the given userName from the controller.
     * @param password  - the given password from the controller.
     * @param firstName - the given firstName from the controller.
     * @param lastName  - the given lastName from the controller.
     * @param birthDate - the given birthDate from the controller.
     * @param residence - the given residence from the controller.
     * @return true if new record was created, else false
     */
    public boolean createUser(String username, String password, String firstName, String lastName, String birthDate, String residence) {

        String sql = "INSERT INTO users (user_name, password, first_name, last_name, birth_date, residence) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, birthDate);
            pstmt.setString(6, residence);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * A function that deletes a record from the DB.
     * The record that should be deleted is the record with the current user's userName
     *
     * @return true if the record was deleted, else false
     */
    public boolean deleteUser() {
        //if (currentUser? !=null){
        String sql = "DELETE FROM users WHERE user_name = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, currentUserName);
            // execute the delete statement
            pstmt.executeUpdate();
            currentUserName = null;
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * A funcion that updates user's details
     *
     * @param username  - the given userName from the controller.
     * @param password  - the given password from the controller.
     * @param firstName - the given firstName from the controller.
     * @param lastName  - the given lastName from the controller.
     * @param birthDate - the given birthDate from the controller.
     * @param residence - the given residence from the controller.
     * @return true if the update process succeeded, else false
     */
    public boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence){
        String sql = "UPDATE users SET user_name = ? , "
                + "password = ? ,"
                + "first_name = ?, "
                + "last_name = ? ,"
                + "birth_date = ? ,"
                + "residence = ? "
                + "WHERE user_name = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, birthDate);
            pstmt.setString(6, residence);
            pstmt.setString(7, currentUserName);
            // update
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }//updateUser

    /**
     * A function that reads the details of the current user or a given user from the controller.
     * and sends the user type to the controller with the details to show on the view.
     *
     * @param userName - current userName or a given one from the controller.
     * @return user type to the controller with the details to show on the view.
     */
    public User showDetails(String userName) {
        String sql = "SELECT user_name, password, first_name,last_name,birth_date,residence " + "FROM users WHERE user_name = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            if (userName == null) pstmt.setString(1, currentUserName);
            else pstmt.setString(1, userName);
            // update
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            currUser = new User(rs.getString("user_name"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("birth_date"), rs.getString("residence"));
            return currUser;


        } catch (SQLException e) {
            // System.out.println(e.getMessage());
            return null;
        }
    }

    public flight showFlightDetails(String flightID) {

        String sql = "SELECT fly_from, destination, depart_Date, return_Date ,num_of_passengers ,luggage_weight, price, supplier_username " + "FROM flights WHERE flight_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, flightID);
            // update
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            flight currFlight = new flight(flightID,rs.getString("fly_from"), rs.getString("destination"), rs.getString("depart_Date"),
                    rs.getString("return_Date"), rs.getString("num_of_passengers"),
                    rs.getString("luggage_weight"), rs.getString("supplier_username"), rs.getString("price"));
            return currFlight;
        } catch (SQLException e) {
            // System.out.println(e.getMessage());
            return null;
        }


}

    /**
     * this function returns the given user details.
     *
     * @param userName - the username we wish to receive details for
     * @return the user details. null- if user not found
     */
    public User searchAndReadUser(String userName) {
        return this.showDetails(userName);
    }

    @Override
    //what fields are there? and how toget them from DB?
    public String getPriceForPayment(String flightID) {
        String sql = "SELECT price " + "FROM flights WHERE flight_ID = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, flightID);
            String priceNum = pstmt.executeQuery().getString("price");//??
            //ResultSet rs  = pstmt.executeQuery();
            //rs.next();
            //return rs.getString("price");
            return priceNum;

        } catch (SQLException e) {
            // System.out.println(e.getMessage());
            return null;
        }
    }

    //Tal
    /**
     * Returns match flights in the DB that correspond to the flight details that were insert
     * @param flightDestination
     * @param departDate
     * @param returnDate
     * @param numOfPassengers
     * @return result set from the query
     */
    public ObservableList<flight> searchFlight(String flightDestination, String departDate, String returnDate, int numOfPassengers) {
        String queryResult;
        String sql = "SELECT flight_id,fly_from, destination, depart_date, return_date,num_of_passengers,luggage_weight, price, supplier_username "
                + "FROM flights WHERE destination = ? AND depart_Date>= '"+departDate+"'"
                + "AND return_Date<='"+returnDate+"'";//TODO: didnt mention here num of pasengers to make evert card theat fit acsseiable even if not all the cards that the buyer needs
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param

            pstmt.setString(1, flightDestination);
            //pstmt.setString(2, departDate);
           // pstmt.setString(3, returnDate);

            ResultSet rs = pstmt.executeQuery();
            ObservableList<flight> data =  FXCollections.observableArrayList();
            while (rs.next()){
                data.add( new flight(rs.getString("flight_id"),rs.getString("fly_from"),rs.getString("destination"),rs.getString("depart_Date"),rs.getString("return_Date"),rs.getString("num_of_passengers"),rs.getString("luggage_weight"),rs.getString("supplier_username"),rs.getString("price")));
            }
            return data;


        } catch (SQLException e) {
            return null;
        }
    }
//Tal
    public boolean createFlight(String flyFrom,String destination, String departureD, String returnD, String numOfPass, String luggageWeight,String price){
        flightCounter=getnextFlightID();
        String sql = "INSERT INTO flights (flight_id,fly_from, destination, depart_Date, return_Date, num_of_passengers, luggage_weight, price, supplier_username) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ""+flightCounter);
            pstmt.setString(2, flyFrom);
            pstmt.setString(3, destination);
            pstmt.setString(4, departureD);
            pstmt.setString(5, returnD);
            pstmt.setString(6, numOfPass);
            pstmt.setString(7, luggageWeight);
            pstmt.setString(8, price);
            pstmt.setString(9, currentUserName);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Tal
    public int getnextFlightID(){
        String sql = "SELECT flight_id "
                + "FROM flights   WHERE   flight_id = (SELECT MAX(flight_id)  FROM flights);";
        int flightNum=1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int flightID=0;
            if(rs.next())
                flightNum=Integer.parseInt(rs.getString("flight_id"))+1;

                return flightNum;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    //tal:
    public ObservableList<flight> getAllFlights(){
        String queryResult;
        String sql = "SELECT *"//" flight_id,fly_from, destination, depart_date, return_date,num_of_passengers,luggage_weight, price, supplier_username "
                + "FROM flights;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param

            //pstmt.setString(1, flightDestination);
            //pstmt.setString(2, departDate);
            // pstmt.setString(3, returnDate);

            ResultSet rs = pstmt.executeQuery();
            ObservableList<flight> data =  FXCollections.observableArrayList();
            while (rs.next()){
                data.add( new flight(rs.getString("flight_id"),rs.getString("fly_from"),rs.getString("destination"),rs.getString("depart_Date"),rs.getString("return_Date"),rs.getString("num_of_passengers"),rs.getString("luggage_weight"),rs.getString("supplier_username"),rs.getString("price")));
            }
            return data;


        } catch (SQLException e) {
            return null;
        }
    }

    public boolean createPurchaseRequest(String flightID, String supplierUserName, String status){
        //flightCounter=getnextFlightID();
        String sql = "INSERT INTO purchaseRequests (purchaseRequest_id, flight_id, supplier_userName, purchaser_userName, status) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ""+getnextPurchaseRequestID());
            pstmt.setString(2, flightID);
            pstmt.setString(3, supplierUserName);
            pstmt.setString(4, currentUserName);
            pstmt.setString(5, status);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Yafit
    public int getnextPurchaseRequestID(){
        String sql = "SELECT purchaseRequest_id "
                + "FROM purchaseRequests   WHERE   purchaseRequest_id = (SELECT MAX(purchaseRequest_id)  FROM purchaseRequests);";
        int flightNum=1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int flightID=0;
            if(rs.next())
                flightNum=Integer.parseInt(rs.getString("purchaseRequest_id"))+1;
            return flightNum;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public boolean updatePurchaseRequestStatus(String purchaseRequestID, String status){
        String sql = "UPDATE purchaseRequests SET status = ? " +
                "WHERE purchaseRequest_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, status);
            pstmt.setString(2, purchaseRequestID);
            // update
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteFlight(String flightID) {
        //if (currentUser? !=null){
        String sql = "DELETE FROM users WHERE flight_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, flightID);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public ObservableList<purchaseRequest> getMyRequest(){

        String sql = "SELECT * "
                + "FROM purchaseRequests  WHERE   purchaser_userName = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserName);
            ResultSet rs = pstmt.executeQuery();
            ObservableList<purchaseRequest> data =  FXCollections.observableArrayList();
            while (rs.next()){
                purchaseRequest p=new purchaseRequest(rs.getString("purchaseRequest_id"),rs.getString("flight_id"),rs.getString("supplier_userName"),rs.getString("purchaser_userName"));
                p.setStatus(rs.getString("status"));
                data.add( p);
            }
            return data;

        } catch (SQLException e) {
            return null;
        }
    }

    public ObservableList<purchaseRequest> requestForSeller(){
        String sql = "SELECT purchaseRequest_id,flight_id,supplier_userName,purchaser_userName,status "
                + "FROM purchaseRequests   WHERE   supplier_userName = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserName);
            ResultSet rs = pstmt.executeQuery();
            ObservableList<vacationClasses.purchaseRequest> data =  FXCollections.observableArrayList();
            while (rs.next()){
                vacationClasses.purchaseRequest p=new vacationClasses.purchaseRequest(rs.getString("purchaseRequest_id"),rs.getString("flight_id"),rs.getString("supplier_userName"),rs.getString("purchaser_userName"));
                p.setStatus(rs.getString("status"));
                data.add( p);
            }
            return data;

        } catch (SQLException e) {
            return null;
        }

    }
}