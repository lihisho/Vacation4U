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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public class CreateUserView extends AView
{
    //Text fields and buttons in the createUser.fxml
    public TextField txtfld_newUsername;
    public TextField txtfld_newPassword;
    public TextField txtfld_passwordConfirmation;
    public DatePicker dateP_dateOfBirth;
    public TextField txtfld_privateName;
    public TextField txtfld_lastName;
    public TextField txtfld_residence;
    public TextField txtfld_phoneNumber;
    public TextField txtfld_email;
    public Button btn_done;
    public Button btn_returnToLogin;

    //getters to all text fields

    public String getNewUsername(){
        return txtfld_newUsername.getText();
    }

    public String getNewPassword(){
        return txtfld_newPassword.getText();
    }

    public String getPasswordConfirmation(){
        return txtfld_passwordConfirmation.getText();
    }

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

    public String getEmail(){
        return txtfld_email.getText();
    }

    public String getPhoneNum(){
        return txtfld_phoneNumber.getText();
    }


    public void setDeafultDate (){
        dateP_dateOfBirth.setValue(LocalDate.of(Year.now().getValue(), MonthDay.now().getMonth().getValue(), MonthDay.now().getDayOfMonth()));
    }

    /**
     * check validation of all the given fields and enter to the DB if evreything is correct.
     */
    public void createNewUser(){
        try{
            //validation of fields
            validateUserName(getNewUsername());
            validatePassword(getNewPassword(),getPasswordConfirmation());
            validateFullName(getPrivateName(),getLastName());
            validateResidence(getResidence());
            validateDateOfBirth(getDateOfBirth());
            validateEmailAddress(getEmail());
            validatePhoneNum(getPhoneNum());
            //if all the fields passed validation, send them to the controller
            if (myController.createNewUser(getNewUsername(),getNewPassword(),
                    getPrivateName(),getLastName(),convertDateToString(getDateOfBirth()),getResidence(),getEmail(),getPhoneNum())){
                //show alert and return to login view
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Creation succeded!");
                alert.setHeaderText(null);
                alert.setContentText("User was created succesfully. Please login with your new user.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                    loadLoginForm();
            }
        }
        catch(Exception exception){       }
    }

    //switch to login view
    public void loadLoginForm() {
        openNewWindow("/login.fxml", btn_done, 500, 300);
    }
}