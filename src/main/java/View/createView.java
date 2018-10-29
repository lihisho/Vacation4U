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

}