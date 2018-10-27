package Model;

import Controller.Controller;

import java.sql.*;

public class MyModel implements IModel {

    private Connection conn = null;

    public MyModel(){

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
    public void disconnect(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Create a new table in the test database
     *
     */
    public void createUsersTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	username TEXT PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	password text NOT NULL,\n"
                + "	private_name TEXT NOT NULL,\n"
                + " last_name TEXT NOT NULL,\n"
                + " birth_date TEXT NOT NULL,\n"
                + " city TEXT NOT NULL\n"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //search according to unique userName
    public boolean searchUserName(String userName){
        String sql="SELECT username FROM users WHERE username = ?";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,userName);
            ResultSet rs =pstm.executeQuery();
            int numberOfRows = rs.getRow();
            if(numberOfRows>0)
                return true;
            return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        //disconnect
    }


    public boolean createUser(String username,String password, String birthDate, String privateName, String lastName, String residence){
        String sql = "INSERT INTO users (username, password, private_name, last_name, birth_date, city) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setString(1,username );
            pstmt.setString(2,password );
            pstmt.setString(3,birthDate );
            pstmt.setString(4,privateName );
            pstmt.setString(5,lastName );
            pstmt.setString(6,residence );
            pstmt.executeUpdate();
            System.out.println("true");
            return true;
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}