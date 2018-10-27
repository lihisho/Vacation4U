package Model;

import Controller.Controller;

import java.sql.*;

public class MyModel {

    private Connection conn = null;

    public MyModel(){
        this.connect();
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

    //search according to unique userName
    public boolean searchUser(String userName){
        String sql="SELECT * FROM users WHERE username equals '"+userName+"'";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
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


    public boolean createUser(String username,String password, String birthDate, String firstName, String lastName, String residence){
        String sql = "INSERT INTO users (username, password, first_name, last_name, birth_date, city)" +
                " VALUES("+username+", "+password+", "+birthDate+", "+firstName+", "+lastName+", "+residence+")";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeQuery(sql);
            return true;
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}