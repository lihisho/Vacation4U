package View;

import Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class AView {

    protected Controller myController;

    /**
     * Sets the view's controller with given controller.
     * @param controller - the specific controller which the view interacts with.
     */
    public void setMyController(Controller controller) {this.myController =controller;}

    //general message displays

    public void displayInformationMessage(String alertMessage, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void displayConfirmationMessage(String alertMessage, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void displayErrorMessage(String alertMessage, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.setHeaderText(null);
        alert.show();
    }

    /**
     * Checks that the given username is valid, meaning, not empty and contains letters only.
     * Also, make sure the username is unique.
     * Indicative error messages are shown if the username does not meet one of the criteria.
     * @param userNameInput - the username which is the user entered.
     * @throws Exception
     */
    protected void validateUserName(String userNameInput)throws Exception {
        //check if the string is not empty
        isNotEmpty(userNameInput);
        //spaces are not allowed in the user name.
        if (userNameInput.contains(" ")) {
            displayErrorMessage("Username can not contain spaces. Please enter a new name", "fail");
            throw new Exception();
        }
        //check whether the given username already exist in database
        if (myController.searchUserName(userNameInput)) {
            displayErrorMessage("The given username already exist. Please choose another username", "fail");
            throw new Exception();
        }
    }

    /**
     * Checks that the given password is valid- meaning, does not contain spaces and matches the password given as
     * confirmation.
     * @param password - the password the user has chosen.
     * @param confirmationPassword - second input of the password for confirmation.
     * @throws Exception
     */
    protected void validatePassword(String password, String confirmationPassword)throws Exception{
        //check if the string is not empty
        isNotEmpty(password);
        isNotEmpty(confirmationPassword);
        //password can not contain spaces
        if( password.contains(" ")){
            displayErrorMessage("Password can not contain spaces. Please enter a new password", "fail");
            throw new Exception();
        }
        //both given passwords must be identical for confirmation
        if( !password.equals(confirmationPassword))
        {
            displayErrorMessage("Password confirmation was denied. Please make sure you enter an identical password", "fail");
            throw new Exception();
        }
    }

    /**
     * Converts local date into string
     * @param date - the date we wish to convert into a string.
     * @return a string representing the given date in the format of 'dd/mm/yyyy'
     */
    protected String convertDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Checks that the user is above the allowed age to use the app.
     * @param dateOfBirth - the given date
     * @throws Exception if the user is under 18.
     */
    protected void validateDateOfBirth(LocalDate dateOfBirth)throws Exception{
        //validate that the user is above the age of 18
        LocalDate currentDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(dateOfBirth,currentDate);
        if (years< 18 ){
            displayErrorMessage("Only users above 18 can use this app.", "fail");
            throw new Exception();
        }
    }

    /**
     * Validate that user's full name contains letters and spaces only, and is not empty.
     * @param privaeName - the user's private name.
     * @param lastName - the user's last name.
     * @throws Exception if the name does not meet the given criteria.
     */
    protected void validateFullName (String privaeName, String lastName) throws Exception{
        //check if the string is not empty
        isNotEmpty(privaeName);
        isNotEmpty(lastName);
        if (!isAlpha(privaeName)){
            displayErrorMessage("Private name can contain letters only.", "fail");
            throw new Exception();
        }
        if (!isAlpha(lastName)){
            displayErrorMessage("Last name can contain letters only.", "fail");
            throw new Exception();
        }
    }

    /**
     * Checks that the city contains letters and spaces only, and not empty.
     * @param givenResidence - the city in which the user lives in.
     * @throws Exception
     */
    protected void validateResidence (String givenResidence)throws Exception{
        //check if the string is not empty
        isNotEmpty(givenResidence);
        if (!isAlpha(givenResidence)){
            displayErrorMessage("Residence name can contain letters only.", "fail");
            throw new Exception();
        }
    }

    //private function which checks whether a given string contains letters or spaces only.
    protected boolean isAlpha(String name){
        char[] chars = name.toCharArray();
        for (char c: chars) {
            if (c == ' ')
                continue;
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that a given string is not empty
     * @param input - a given input.
     * @throws Exception if the string is empty.
     */
    protected void isNotEmpty (String input) throws Exception{
        if (input.isEmpty()){
            displayErrorMessage("All fields must be filled.", "Failed");
            throw new Exception();
        }
    }

    /**
     * opens a new scene above the primary stage
     * @param fxmlName - the fmxl we wish to load.
     * @param btn - a button which appears on the current scene. used in order to get the current stage.
     */
    public void openNewWindow(String fxmlName, Button btn , int width, int height){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource(fxmlName).openStream();
            Parent parent = fxmlLoader.load(is);
            AView newView = fxmlLoader.getController();
            newView.setMyController(this.myController);
            Scene newScene = new Scene(parent,width,height);
            Stage curStage = (Stage) btn.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Checks that the user add future flight
     * @param dateToCheck - the given date
     */
    protected void validateFlightDate(LocalDate dateToCheck)throws Exception{
        //validate that the user is above the age of 18
        LocalDate currentDate = LocalDate.now();
        if(dateToCheck==null||!dateToCheck.isAfter(currentDate)) {
            displayErrorMessage("You can add only future flights.", "fail");
            throw new Exception();
        }

    }

    protected void validateDestination(String destination)throws Exception{
        //validate that the user is above the age of 18
        LocalDate currentDate = LocalDate.now();
        if (destination==null||destination.equals("") ){
            displayErrorMessage("you must enter this field.", "fail");
            throw new Exception();
        }
    }
}//AView
