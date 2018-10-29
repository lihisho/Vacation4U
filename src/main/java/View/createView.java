package View;

import Controller.Controller;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public class createView extends AView
{
    public TextField txtfld_newUsername;
    public TextField txtfld_newPassword;
    public TextField txtfld_passwordConfirmation;
    public DatePicker dateP_dateOfBirth;
    public TextField txtfld_privateName;
    public TextField txtfld_lastName;
    public TextField txtfld_residence;
    public Button btn_done;
    public Button btn_returnToLogin;

    public String getNewUsername(){
        return txtfld_newUsername.getText();
    }

    public String getNewPassword(){
        return txtfld_newPassword.getText();
    }

    public String getPasswordConfirmation(){
        return txtfld_passwordConfirmation.getText();
    }
//need to check
    public LocalDate getDateOfBirth(){
        return dateP_dateOfBirth.getValue();
    }

    public String getPrivateName(){
        return txtfld_privateName.getText();
    }

    public String getLastName(){
        return txtfld_lastName.getText();
    }

    public String getResidence(){
        return txtfld_residence.getText();
    }

    public void displayInformationMessage(String alertMessage, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.show();
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
        alert.setHeaderText(null);
        alert.setContentText(alertMessage);
        alert.show();
    }


    public void createNewUser(){
        //validation of fields
        try{
            validateUserName(getNewUsername());
            validatePassword(getNewPassword(),getPasswordConfirmation());
            validateDateOfBirth(getDateOfBirth());
            validateFullName(getPrivateName(),getLastName());
            validateResidence(getResidence());
            if (myController.createNewUser(getNewUsername(),getNewPassword(),
                    getPrivateName(),getLastName(),convertDateToString(getDateOfBirth()),getResidence())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Creation succeded!");
                alert.setHeaderText(null);
                alert.setContentText("User was created succesfully. Please login with your new user.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                    loadLoginForm();
            }
        }
        catch(Exception exception){

        }
    }

    public void loadLoginForm(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource("/Login.fxml").openStream();
            Parent loginForm = fxmlLoader.load(is);
            AView loginView =fxmlLoader.getController();
            loginView.setMyController(this.myController);
            Scene newScene = new Scene(loginForm,600,400);
            Stage curStage = (Stage) btn_done.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void validateUserName(String userNameInput)throws Exception {
        //check if the string is not empty
        isNotEmpty(userNameInput);
        //spaces are not allowed in the user name.
        if (userNameInput.contains(" ")) {
            displayErrorMessage("Username can not contain spaces. Please enter a new name", "Failed");
            throw new Exception();
        }
        //check whether the given username already exist in database
        if (myController.searchUserName(userNameInput)) {
            displayErrorMessage("The given username already exist. Please choose another username", "Failed");
            throw new Exception();
        }

    }

    private void validatePassword(String password, String confirmationPassword)throws Exception{
        //check if the string is not empty
        isNotEmpty(password);
        isNotEmpty(confirmationPassword);
        //password can not contain spaces
        if( password.contains(" ")){
            displayErrorMessage("Password can not contain spaces. Please enter a new password", "Failed");
            throw new Exception();
        }
        //both given passwords must be identical for confirmation
        if( !password.equals(confirmationPassword))
        {
            displayErrorMessage("Password confirmation was denied. Please make sure you entered an identical password.", "Failed");
            throw new Exception();
        }
    }

    private String convertDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void validateDateOfBirth(LocalDate dateOfBirth)throws Exception{
        isNotEmpty(convertDateToString(dateOfBirth));
        //validate that the user is above the age of 18
        LocalDate currentDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(dateOfBirth,currentDate);
        System.out.println(years);
        if (years< 18 ){
            displayErrorMessage("Only users above 18 can use this app.", "Failed");
            throw new Exception();
        }
    }

    private void validateFullName (String privaeName, String lastName) throws Exception{
        //check if the string is not empty
        isNotEmpty(privaeName);
        isNotEmpty(lastName);
        if (!isAlpha(privaeName)){
            displayErrorMessage("Private name can contain letters only.", "Failed");
            throw new Exception();
        }
        if (!isAlpha(lastName)){
            displayErrorMessage("Last name can contain letters only.", "Failed");
            throw new Exception();
        }
    }

    private void validateResidence (String givenResidence)throws Exception{
        //check if the string is not empty
        isNotEmpty(givenResidence);
        if (!isAlpha(givenResidence)){
            displayErrorMessage("Residence name can contain letters only.", "Failed");
            throw new Exception();
        }
    }

    //private function which checks whether a given string containes letters only.
    private boolean isAlpha(String name){
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

    private void isNotEmpty (String input) throws Exception{
        if (input.isEmpty()){
            displayErrorMessage("All fields must be filled.", "Failed");
            throw new Exception();
        }
    }
}