package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vacationClasses.User;
import vacationClasses.Vacation;
import vacationClasses.purchaseRequest;
import vacationClasses.swapRequest;

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
    //Vacation ID counter
    private int vacationCounter;

    //constructor
    public MyModel() {
        //connect the DB
        this.connect();
        //create users table in the DB, if doesn't exist
        this.createUsersTable();
        //create vacations table in the DB
        this.createVacationTable();
        //create purchase requests table in the DB
        this.createPurchaseRequestTable();
        this.createSwapRequestsTable();
        vacationCounter =1;
    }

    /**
     * A function that returns an IModel singleton instance
     * @return instance of IModel
     */
    public static IModel getInstance() {
        if (singleton == null)
            singleton = new MyModel();
        return singleton;

    }
    public String getCurrUserName(){
        return currentUserName;
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
     * Creates a new users table in the database, if doesn't exist
     */
    public void createUsersTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	user_name TEXT PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	password TEXT NOT NULL,\n"
                + "	first_name TEXT NOT NULL,\n"
                + " last_name TEXT NOT NULL,\n"
                + " birth_date TEXT NOT NULL,\n"
                + " residence TEXT NOT NULL,\n"
                + " phone_num TEXT NOT NULL,\n"
                + " email TEXT NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new table containing all requests for swap which are currently in process.
     */
    private void createSwapRequestsTable(){
        // SQL statement for creating a new table
        String sql= "CREATE TABLE IF NOT EXISTS SwapRequests (\n"
                + "swapRequest_id TEXT PRIMARY KEY NOT NULL UNIQUE,\n"
                + "seller_side_vacation_id TEXT NOT NULL,\n"
                + "seller_side_userName TEXT NOT NULL,\n"
                + "buyer_side_vacation_id  TEXT NOT NULL,\n"
                + "buyer_side_userName TEXT NOT NULL,\n"
                + "status TEXT NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a new vacation table in the database, if doesn't exist
     */
    public void createVacationTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Vacations (\n"
                + "	vacation_id TEXT PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	departure_from TEXT NOT NULL,\n"
                + "	destination TEXT NOT NULL,\n"
                + "	depart_Date TEXT NOT NULL,\n"
                + " return_Date TEXT NOT NULL,\n"
                + " airline TEXT NOT NULL,\n"
                + " luggage_included TEXT NOT NULL,\n"
                + " luggage_weight TEXT DEFAULT 'None',\n"
                + " num_of_passengers TEXT NOT NULL,\n"
                + " tickets_type TEXT NOT NULL,\n"
                + " hotel TEXT DEFAULT 'None',\n"
                + " hotel_rank TEXT DEFAULT 'None',\n"
                + " vacation_type TEXT DEFAULT 'None',\n"
                + " origin_price TEXT NOT NULL,\n"
                + " price_offered TEXT NOT NULL DEFAULT '-1',\n"
                + " supplier_username TEXT NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a new purchase requests table in the database, if doesn't exist
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
    }

    /**
     * A function that creates a new record  in the user table in the DB.
     * @param username  - the given userName from the controller.
     * @param password  - the given password from the controller.
     * @param firstName - the given firstName from the controller.
     * @param lastName  - the given lastName from the controller.
     * @param birthDate - the given birthDate from the controller.
     * @param residence - the given residence from the controller.
     * @return true if new record was created, else false
     */
    public boolean createUser(String username, String password, String firstName, String lastName, String birthDate, String residence,String email,String phoneNum) {

        String sql = "INSERT INTO users (user_name, password, first_name, last_name, birth_date, residence, phone_num, email) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, birthDate);
            pstmt.setString(6, residence);
            pstmt.setString(7, phoneNum);
            pstmt.setString(8, email);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * this function returns the given user details.
     * @param userName - the username we wish to receive details for
     * @return the user details. null- if user not found
     */
    public User searchAndReadUser(String userName) {
        return this.showDetails(userName);
    }

    /**
     * A function that deletes a record from the DB.
     * The record that should be deleted is the record with the current user's userName
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
     * A function that updates user's details
     * @param username  - the given userName from the controller.
     * @param password  - the given password from the controller.
     * @param firstName - the given firstName from the controller.
     * @param lastName  - the given lastName from the controller.
     * @param birthDate - the given birthDate from the controller.
     * @param residence - the given residence from the controller.
     * @return true if the update process succeeded, else false
     */
    public boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence, String phoneNum,String email){
        String sql = "UPDATE users SET user_name = ? , "
                + "password = ? ,"
                + "first_name = ?, "
                + "last_name = ? ,"
                + "birth_date = ? ,"
                + "residence = ? ,"
                + "phone_num = ? ,"
                + "email = ? "
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
            pstmt.setString(7, phoneNum);
            pstmt.setString(8, email);
            pstmt.setString(9, currentUserName);
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
     * @param userName - current userName or a given one from the controller.
     * @return user type to the controller with the details to show on the view.
     */
    public User showDetails(String userName) {
        String sql = "SELECT user_name, password, first_name,last_name,birth_date,residence,phone_num,email " + "FROM users WHERE user_name = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            if (userName == null) pstmt.setString(1, currentUserName);
            else pstmt.setString(1, userName);
            // update
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            currUser = new User(rs.getString("user_name"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("birth_date"), rs.getString("residence"),rs.getString("phone_num"),rs.getString("email"));
            return currUser;


        } catch (SQLException e) {
            // System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * A function that returns a vacation with all of it's details
     * @param vacationID - the vacationID, that identifies the vacation
     * @return
     */
    public Vacation showVacationDetails(String vacationID) {
        String sql = "SELECT departure_from, destination, depart_Date, return_Date ,airline, " +
                "luggage_included, luggage_weight, num_of_passengers ,tickets_type, hotel, hotel_rank," +
                "vacation_type, origin_price, price_offered, supplier_username "
                + "FROM Vacations WHERE vacation_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, vacationID);
            // update
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Vacation currVacation = new Vacation(vacationID, rs.getString("departure_from"), rs.getString("destination"),
                    rs.getString("depart_Date"), rs.getString("return_Date"), rs.getString("airline"),
                    rs.getString("luggage_included"), rs.getString("luggage_weight") , rs.getString("num_of_passengers"),
                    rs.getString("tickets_type"),rs.getString("hotel"),rs.getString("hotel_rank"),
                    rs.getString("vacation_type"),rs.getString("origin_price"), rs.getString("price_offered"),
                    rs.getString("supplier_username"));
            return currVacation;
        } catch (SQLException e) {
            return null;
        }
    }


    @Override

    /**
     * A function that returns the offered price from the DB
     */
    public String getPriceForPayment(String vacationID) {
        String sql = "SELECT price_offered " + "FROM Vacations WHERE vacation_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vacationID);
            String priceNum = pstmt.executeQuery().getString("price_offered");
            return priceNum;

        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Returns match flights in the DB that correspond to the Vacation details that were insert
     * @param vacationDestination
     * @param departDate
     * @param returnDate
     * @param numOfPassengers
     * @return result set from the query
     */
    public ObservableList<Vacation> searchVacationToBuy(String vacationDestination, String departDate, String returnDate, String numOfPassengers) {
        String queryResult;
        String sql = "SELECT vacation_id,departure_from, destination, depart_Date, return_Date ,airline, " +
                "luggage_included, luggage_weight, num_of_passengers ,tickets_type, hotel, hotel_rank," +
                "vacation_type, origin_price, price_offered, supplier_username "
                + "FROM Vacations WHERE ";
        //all fields are full
        if (!vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"' AND num_of_passengers >= '"+numOfPassengers+"'";
        //vacationDestination.isEmpty()
        else if (vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"' AND num_of_passengers >= '"+numOfPassengers+"'";
        //departDate.isEmpty() && returnDate.isEmpty()
        else if (!vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ? AND num_of_passengers >= '"+numOfPassengers+"'";
        //numOfPassengers.isEmpty()
        else if (!vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"'";
        //vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty()
        else if (vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"supplier_username <> ? AND num_of_passengers >= '"+numOfPassengers+"'";
        //vacationDestination.isEmpty() && numOfPassengers.isEmpty()
        else if (vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && numOfPassengers.isEmpty())
            sql = sql+"supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"'";
        //departDate.isEmpty() && returnDate.isEmpty() && numOfPassengers.isEmpty()
        else if (!vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty() && numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ?";
        sql =sql+" AND price_offered<>'-1'";

        try {//TODO: Lihi changed: user cant find his own flights.
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserName);

            ResultSet rs = pstmt.executeQuery();
            ObservableList<Vacation> data =  FXCollections.observableArrayList();
            while (rs.next()){
                data.add( new Vacation(rs.getString("vacation_id"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("depart_Date"), rs.getString("return_Date"), rs.getString("airline"),
                        rs.getString("luggage_included"), rs.getString("luggage_weight") , rs.getString("num_of_passengers"),
                        rs.getString("tickets_type"),rs.getString("hotel"),rs.getString("hotel_rank"),
                        rs.getString("vacation_type"),rs.getString("origin_price"), rs.getString("price_offered"),
                        rs.getString("supplier_username")));
            }
            return data;
        } catch (SQLException e) {
            return null;
        }
    }
    public ObservableList<Vacation> searchVacationToSwap(String vacationDestination, String departDate, String returnDate, String numOfPassengers) {
        String queryResult;
        String sql = "SELECT vacation_id,departure_from, destination, depart_Date, return_Date ,airline," +
                " luggage_included, luggage_weight, num_of_passengers ,tickets_type, hotel, hotel_rank," +
                " vacation_type, origin_price, price_offered, supplier_username "
                + "FROM Vacations WHERE ";
        //all fields are full
        if (!vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"' AND num_of_passengers >= '"+numOfPassengers+"'";
            //vacationDestination.isEmpty()
        else if (vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"' AND num_of_passengers >= '"+numOfPassengers+"'";
            //departDate.isEmpty() && returnDate.isEmpty()
        else if (!vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ? AND num_of_passengers >= '"+numOfPassengers+"'";
            //numOfPassengers.isEmpty()
        else if (!vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"'";
            //vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty()
        else if (vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty() && !numOfPassengers.isEmpty())
            sql = sql+"supplier_username <> ? AND num_of_passengers >= '"+numOfPassengers+"'";
            //vacationDestination.isEmpty() && numOfPassengers.isEmpty()
        else if (vacationDestination.isEmpty() && !departDate.isEmpty() && !returnDate.isEmpty() && numOfPassengers.isEmpty())
            sql = sql+"supplier_username <> ? AND depart_Date>= '"+departDate+"' AND return_Date<='"+returnDate+"'";
            //departDate.isEmpty() && returnDate.isEmpty() && numOfPassengers.isEmpty()
        else if (!vacationDestination.isEmpty() && departDate.isEmpty() && returnDate.isEmpty() && numOfPassengers.isEmpty())
            sql = sql+"destination = '"+vacationDestination+"' AND supplier_username <> ?";
        sql = sql+" AND price_offered = '-1'";

        try {//TODO: Lihi changed: user cant find his own flights.
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserName);
            //pstmt.setString(2, departDate);
            // pstmt.setString(3, returnDate);

            ResultSet rs = pstmt.executeQuery();
            ObservableList<Vacation> data =  FXCollections.observableArrayList();
            while (rs.next()){
                data.add( new Vacation(rs.getString("vacation_id"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("depart_Date"), rs.getString("return_Date"), rs.getString("airline"),
                        rs.getString("luggage_included"), rs.getString("luggage_weight") , rs.getString("num_of_passengers"),
                        rs.getString("tickets_type"),rs.getString("hotel"),rs.getString("hotel_rank"),
                        rs.getString("vacation_type"),rs.getString("origin_price"), rs.getString("price_offered"),
                        rs.getString("supplier_username")));
            }
            return data;
        } catch (SQLException e) {
            return null;
        }
    }
    /**
     * A function that creates new record of vacation in the DB
     * @param departureFrom
     * @param destination
     * @param departureD
     * @param returnD
     * @param numOfPass
     * @param luggageWeight
     * @param priceOffered
     * @return true if creation succeeded, otherwise false
     */
    public boolean createVacation(String departureFrom, String destination, String departureD, String returnD,
                                  String airline, String luggageIncluded, String luggageWeight, String numOfPass,
                                  String ticketsType, String hotel, String hotelRank, String vacationType, String originPrice,
                                  String priceOffered){
        vacationCounter = getNextVacationID();
        String sql = "INSERT INTO Vacations (vacation_id, departure_from, destination, depart_Date, return_Date ,airline," +
                "luggage_included, luggage_weight, num_of_passengers ,tickets_type, hotel, hotel_rank," +
                "vacation_type, origin_price, price_offered, supplier_username)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ""+ vacationCounter);
            pstmt.setString(2, departureFrom);
            pstmt.setString(3, destination);
            pstmt.setString(4, departureD);
            pstmt.setString(5, returnD);
            pstmt.setString(6, airline);
            pstmt.setString(7, luggageIncluded);
            pstmt.setString(8, luggageWeight);
            pstmt.setString(9, numOfPass);
            pstmt.setString(10, ticketsType);
            pstmt.setString(11, hotel);
            pstmt.setString(12, hotelRank);
            pstmt.setString(13, vacationType);
            pstmt.setString(14, originPrice);
            pstmt.setString(15, priceOffered);
            pstmt.setString(16, currentUserName);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * A function that returns a unique vacationID
     * @return the new vacationID
     */
    public int getNextVacationID(){
        String sql = "SELECT vacation_id "
                + "FROM Vacations   WHERE   vacation_id = (SELECT MAX(vacation_id)  FROM Vacations);";
        int vacationNum=1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                vacationNum=Integer.parseInt(rs.getString("vacation_id"))+1;
                return vacationNum;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /**
     * A function that returns all the vacations in the DB
     * @return ObservableList of vacations
     */
    public ObservableList<Vacation> getAllVacations(){
        String sql = "SELECT *"
                + "FROM Vacations;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ObservableList<Vacation> data =  FXCollections.observableArrayList();
            while (rs.next()){
                data.add( new Vacation(rs.getString("vacation_id"), rs.getString("departure_from"), rs.getString("destination"),
                        rs.getString("depart_Date"), rs.getString("return_Date"), rs.getString("airline"),
                        rs.getString("luggage_included"), rs.getString("luggage_weight") , rs.getString("num_of_passengers"),
                        rs.getString("tickets_type"),rs.getString("hotel"),rs.getString("hotel_rank"),
                        rs.getString("vacation_type"),rs.getString("origin_price"), rs.getString("price_offered"),
                        rs.getString("supplier_username")));
            }
            return data;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * A function that creates new record of purchase request in the DB.
     * @param flightID
     * @param supplierUserName
     * @param status
     * @return true if creation succeeded, otherwise false
     */
    public boolean createPurchaseRequest(String flightID, String supplierUserName, String status){
        String sql = "INSERT INTO purchaseRequests (purchaseRequest_id, flight_id, supplier_userName, purchaser_userName, status) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ""+ getNextPurchaseRequestID());
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

    public boolean createSwapRequest(String vacationID, String supplierUserName,String vacationIDForSwap, String status) {
        String sql = "INSERT INTO SwapRequests (swapRequest_id, seller_side_vacation_id, seller_side_userName, buyer_side_vacation_id, buyer_side_userName, status) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ""+getnextSwapRequestID());
            pstmt.setString(2, vacationID);
            pstmt.setString(3, supplierUserName);
            pstmt.setString(4, vacationIDForSwap);
            pstmt.setString(5, currentUserName);
            pstmt.setString(6, status);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * A function that returns a unique purchaseRequestID
     * @return the new purchaseRequestID
     */
    public int getNextPurchaseRequestID(){
        String sql = "SELECT purchaseRequest_id "
                + "FROM purchaseRequests   WHERE   purchaseRequest_id = (SELECT MAX(purchaseRequest_id)  FROM purchaseRequests);";
        int purchaseRequestNum=1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                purchaseRequestNum=Integer.parseInt(rs.getString("purchaseRequest_id"))+1;
            return purchaseRequestNum;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getnextSwapRequestID(){
        String sql = "SELECT swapRequest_id "
                + "FROM SwapRequests   WHERE   swapRequest_id = (SELECT MAX(swapRequest_id)  FROM SwapRequests);";
        int reqNum=0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                reqNum=Integer.parseInt(rs.getString("swapRequest_id"))+1;
            return reqNum;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /**
     * A function that update the status of a given purchase request
     * @param purchaseRequestID
     * @param status
     * @return true if update succeeded, otherwise false
     */
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

    @SuppressWarnings("Duplicates")
    public boolean updateSwapRequestStatus(String swapRequestId, String status){
        String sql = "UPDATE SwapRequests SET status = ? " +
                "WHERE swapRequest_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding parameter
            pstmt.setString(1, status);
            pstmt.setString(2, swapRequestId);
            // update
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
    /**
     * A function that deletes a record of vacation from the DB
     * @param vacationID
     * @return true if deletion succeeded, otherwise false
     */
    public boolean deleteVacation(String vacationID) {
        String sql = "DELETE FROM Vacations WHERE vacation_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, vacationID);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * A function that returns all the purchase requests in the DB of the current user
     * @return ObservableList of purchaseRequests
     */
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

    @SuppressWarnings("Duplicates")
    public ObservableList<swapRequest> myHandedSwapRequests(){
        String sql= "SELECT swapRequest_id,seller_side_vacation_id,seller_side_userName,buyer_side_vacation_id," +
                "buyer_side_userName,status FROM SwapRequests WHERE buyer_side_username =?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserName);
            ResultSet rs = pstmt.executeQuery();
            ObservableList<vacationClasses.swapRequest> data =  FXCollections.observableArrayList();
            while (rs.next()){
                vacationClasses.swapRequest s=new vacationClasses.swapRequest(rs.getString("swapRequest_id"),rs.getString("seller_side_vacation_id"),
                        rs.getString("seller_side_userNAme"),rs.getString("buyer_side_vacation_id"),rs.getString("buyer_side_userName"),
                        rs.getString("status"));
                s.setSwapStatus(rs.getString("status"));
                data.add( s);
            }
            return data;
        }catch(Exception e){
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    public ObservableList<swapRequest> swapRequestsForSeller(){
        String sql= "SELECT swapRequest_id,seller_side_vacation_id,seller_side_userName,buyer_side_vacation_id," +
                "buyer_side_userName,status FROM SwapRequests WHERE seller_side_userName =?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserName);
            ResultSet rs = pstmt.executeQuery();
            ObservableList<vacationClasses.swapRequest> data =  FXCollections.observableArrayList();
            while (rs.next()){
                vacationClasses.swapRequest s=new vacationClasses.swapRequest(rs.getString("swapRequest_id"),rs.getString("seller_side_vacation_id"),
                        rs.getString("seller_side_userNAme"),rs.getString("buyer_side_vacation_id"),rs.getString("buyer_side_userName"),
                        rs.getString("status"));
                s.setSwapStatus(rs.getString("status"));
                data.add( s);
            }
            return data;
        }catch(Exception e){
            return null;
        }
    }


    /**
     * A function that returns all the purchase requests for Approval in the DB of the current user
     * @return ObservableList of purchaseRequests
     */
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
    public User getOwnerDetailes(String vacationID) {
        String sql = "SELECT first_name,last_name,phone_num,email FROM users WHERE user_name=(SELECT supplier_username "
                + "FROM Vacations   WHERE   vacation_id = ? );";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, vacationID);
            ResultSet rs = pstmt.executeQuery();
            String userName;
            String email;
            String phoneNumber;
            User sellerUser = new User();
            if (rs.next()) {
                sellerUser.setFirstname(rs.getString("first_name"));
                sellerUser.setLastname(rs.getString("last_name"));
                sellerUser.setEmail(rs.getString("email"));
                sellerUser.setPhoneNumber(rs.getString("phone_num"));
            }

            return sellerUser;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("Duplicates")
    public boolean deleteDonePurcahesReq(String  purchaseRequestID) {
        String sql = "DELETE FROM purchaseRequests WHERE purchaseRequest_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, purchaseRequestID);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("Duplicates")
    public boolean deleteDoneSwapsReq(String  swapReqID) {
        String sql = "DELETE FROM SwapRequests WHERE swapRequest_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, swapReqID);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean searchEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) return true;
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ObservableList<Vacation> getMyVacationToSwitch(){
        String sql = "SELECT * "
                + "FROM Vacations  WHERE supplier_username = ? AND price_offered=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserName);
            pstmt.setString(2, "-1");
            ResultSet rs = pstmt.executeQuery();
            ObservableList<Vacation> data =  FXCollections.observableArrayList();

            while (rs.next()) {
                Vacation v = new Vacation(rs.getString("vacation_id"), rs.getString("departure_from"), rs.getString("destination"), rs.getString("depart_Date"), rs.getString("return_Date"),
                        rs.getString("airline"), rs.getString("luggage_included"), rs.getString("luggage_weight"), rs.getString("num_of_passengers"), rs.getString("tickets_type"), rs.getString("hotel"),
                        rs.getString("hotel_rank"), rs.getString("vacation_type"), rs.getString("origin_price"), rs.getString("price_offered"), rs.getString("supplier_username"));
                data.add(v);
            }
            return data;

        } catch (SQLException e) {
        return null;
        }
    }
}