package View;

import Model.MyModel;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import java.time.LocalDate;

public class updateView extends AView{

    public TextField txtfld_newUsername;
    public TextField txtfld_newPassword;
    public TextField txtfld_passwordConfirmation;
    public DatePicker dateP_dateOfBirth;
    public TextField txtfld_privateName;
    public TextField txtfld_lastName;
    public TextField txtfld_residence;
    public Button btn_update;
    User currUser;

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

    public void updateUser(){
        try{
            if(!currUser.getUsername().equals(getNewUsername()))
                validateUserName(getNewUsername());
            if(!currUser.getPassword().equals(getNewPassword()))
                validatePassword(getNewPassword(),getPasswordConfirmation());
            validateDateOfBirth(getDateOfBirth());
            validateFullName(getPrivateName(),getLastName());
            validateResidence(getResidence());
            if (myController.updateUser(getNewUsername(),getNewPassword(),
                    getPrivateName(),getLastName(),convertDateToString(getDateOfBirth()),getResidence())) {
                displayInformationMessage("User was updated successfully.", "update succeeded");
                /////// find out how to delay the switch till after the users presses OK!
                backToActionScreen();
            }
        }
        catch(Exception exception) {

        }
    }

    public void setValues(){
        currUser= myController.showDetails(null);
        txtfld_newUsername.setText(currUser.getUsername());
        txtfld_newPassword.setText(currUser.getPassword());
        txtfld_privateName.setText(currUser.getFirstname());
        txtfld_lastName.setText(currUser.getLastname());
        String[] parts = currUser.getBirthdate().split("/");

        dateP_dateOfBirth.setValue(LocalDate.of(Integer.parseInt(parts[2]),Integer.parseInt(parts[1]),Integer.parseInt(parts[0])));
        txtfld_residence.setText(currUser.getResidence());
    }


    public void backToActionScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource("/actionsScreen.fxml").openStream();
            Parent updateForm = fxmlLoader.load(is);
            AView action = fxmlLoader.getController();
            action.setMyController(this.myController);
            Scene newScene = new Scene(updateForm,600  ,600);
            Stage curStage = (Stage) btn_update.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
