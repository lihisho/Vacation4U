package Model;

import Controller.Controller;

import java.sql.*;

public class MyModel implements IModel {

    private Connection conn = null;
    //the current user which is now connected to the system.
    private String currentUserName = null;
    private User currUser;
    public MyModel() {

        this.connect();
        this.createUsersTable();
    }

    //connect to the Database
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:Vacation4UDatabase.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //close the connection to the DB
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
            System.out.println(e.getMessage());
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

    //search according to unique userName
    public boolean searchUserName(String userName) {
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
            System.out.println("true");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

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
    //update user's details
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
            pstmt.setString(7, username);
            // update
            pstmt.executeUpdate();
            System.out.println("updated");// delete this
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }//updateUser

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
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User searchAndReadUser(String userName)
    {
        return this.showDetails(userName);
    }

}