package Model;

import Controller.Controller;

import java.sql.*;

/**
 * A Model class as part of the MVC model.
 * Our Model holds the DB of the system and all the actions on the DB are in this class
 */
public class MyModel implements IModel {

    //a field that describes the connection to the DB
    private Connection conn = null;
    //the current userName which is now connected to the system.
    private String currentUserName = null;
    //the current user which is now connected to the system.
    private User currUser;

    //constructor
    public MyModel() {
        //connect the DB
        this.connect();
        //create user table in the DB, if doesn't exist
        this.createUsersTable();
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
     * @param username - the username that wish to log in to the system.
     * @param password - the username's password.
     * @return true if the user logged successfully.
     */
    public boolean login(String username, String password){
        //search if the given username exists in the database
        if(!searchUserName(username))
            return false;
        //check whether the password is correct
        String sql= "SELECT password FROM users WHERE user_name = ?";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1,username);
            ResultSet rs =pstm.executeQuery();
            rs.next();//makes the first row the current row.
            if(rs.getString(1).equals(password))
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

    /**
     * A function that search according to unique userName
     * @param userName - a given userName to search by from the controller
     * @return true if the search process passed succesfully, else false
     */    public boolean searchUserName(String userName) {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1, userName);
            ResultSet rs = pstm.executeQuery();
            if (rs.next())
                return true;
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        //disconnect
    }

    /**
     * A function that creates a new record  in the user table in the DB.
     * @param username - the given userName from the controller.
     * @param password - the given password from the controller.
     * @param firstName - the given firstName from the controller.
     * @param lastName - the given lastName from the controller.
     * @param birthDate - the given birthDate from the controller.
     * @param residence - the given residence from the controller.
     * @return true if new record was created, else false
     */
    public boolean createUser(String username,String password, String firstName, String lastName, String birthDate, String residence){

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
     * @return true if the record was deleted, else false
     */
    public boolean deleteUser(){
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
     * @param username - the given userName from the controller.
     * @param password - the given password from the controller.
     * @param firstName - the given firstName from the controller.
     * @param lastName - the given lastName from the controller.
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
            PreparedStatement pstmt =conn.prepareStatement(sql);

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
     * @param userName - current userName or a given one from the controller.
     * @return user type to the controller with the details to show on the view.
     */
    public User showDetails(String userName){
        String sql = "SELECT user_name, password, first_name,last_name,birth_date,residence "
                + "FROM users WHERE user_name = ?";

        try {
            PreparedStatement pstmt =conn.prepareStatement(sql);

            // set the corresponding param
            if(userName == null)
                pstmt.setString(1, currentUserName);
            else
                pstmt.setString(1,userName);
            // update
            ResultSet rs  = pstmt.executeQuery();
            rs.next();
            currUser=new User(rs.getString("user_name"),rs.getString("password"), rs.getString("first_name"),rs.getString("last_name"),rs.getString("birth_date"),rs.getString("residence"));
            return currUser;


        } catch (SQLException e) {
           // System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     *this function returns the given user details.
     * @param userName - the username we wish to receive details for
     * @return the user details. null- if user not found
     */
    public User searchAndReadUser(String userName)
    {
        return this.showDetails(userName);
    }

}