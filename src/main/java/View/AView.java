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

    public void setMyController(Controller controller) {this.myController =controller;}

    public void displayInformationMessage(String alertMessage, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
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

    protected String convertDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    protected void validateDateOfBirth(LocalDate dateOfBirth)throws Exception{
        //validate that the user is above the age of 18
        LocalDate currentDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(dateOfBirth,currentDate);
        System.out.println(years);
        if (years< 18 ){
            displayErrorMessage("Only users above 18 can use this app.", "fail");
            throw new Exception();
        }
    }

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

    //checks the the string is not empty
    protected void isNotEmpty (String input) throws Exception{
        if (input.isEmpty()){
            displayErrorMessage("All fields must be filled.", "Failed");
            throw new Exception();
        }
    }

    public void openNewWindow(String fxmlName, Button btn){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource (fxmlName).openStream();
            Parent parent = fxmlLoader.load(is);
            AView newView = fxmlLoader.getController();
            newView.setMyController(this.myController);
            Scene newScene = new Scene(parent,600  ,600);
            Stage curStage = (Stage) btn.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
