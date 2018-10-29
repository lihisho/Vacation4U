package View;

import Model.MyModel;
import Model.User;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

            validatePassword(getNewPassword(),getPasswordConfirmation());
            validateDateOfBirth(getDateOfBirth());
            validateFullName(getPrivateName(),getLastName());
            validateResidence(getResidence());
            if (myController.updateUser(getNewUsername(),getNewPassword(),
                    getPrivateName(),getLastName(),convertDateToString(getDateOfBirth()),getResidence())) {
                displayInformationMessage("Model.User was updated successfully.", "update succeeded");

            }
        }
        catch(Exception exception) {

        }
    }

    public void setValues(){
         currUser= myController.showDetails();
        txtfld_newUsername.setText(currUser.getUsername());
        txtfld_newPassword.setText(currUser.getPassword());
        txtfld_privateName.setText(currUser.getFirstname());
        txtfld_lastName.setText(currUser.getLastname());
        String[] parts = currUser.getBirthdate().split("/");
        dateP_dateOfBirth.setValue(LocalDate.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));
        txtfld_residence.setText(currUser.getResidence());
    }
}
