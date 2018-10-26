package View;

import javafx.scene.control.DatePicker;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class createView
{
    public TextField txtfld_newUsername;
    public TextField txtfld_newPassword;
    public TextField txtfld_passwordConfirmation;
    public DatePicker dateP_dateOfBirth;
    public TextField txtfld_privateName;
    public TextField txtfld_lastName;
    public TextField txtfld_residence;
    public Button btn_done;

    public String getNewUsername(){
        return txtfld_newUsername.getText();
    }

    public String getNewPassword(){
        return txtfld_newPassword.getText();
    }

    public String getPasswordConfirmation(){
        return txtfld_passwordConfirmation.getText();
    }

    public String getDateOfBirth(){

        String date= dateP_dateOfBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date;
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

}