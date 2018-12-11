package View;

import vacationClasses.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.time.LocalDate;

public class updateView extends AView{

    //Text fields and buttons in the update.fxml
    public TextField txtfld_newUsername;
    public TextField txtfld_newPassword;
    public TextField txtfld_passwordConfirmation;
    public DatePicker dateP_dateOfBirth;
    public TextField txtfld_privateName;
    public TextField txtfld_lastName;
    public TextField txtfld_residence;
    public Button btn_update;
    //current user that login in the system
    User currUser;

    //getters
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

    /**
     * A function that validates user's details before sending them to the controller
     */
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
                backToActionScreen();
            }
        }
        catch(Exception exception) {

        }
    }

    /**
     * A function sets values to shoe in the text fields
     */
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

    /**
     * A function that go back to ActionScreen
     */
    public void backToActionScreen(){
        openNewWindow("/actionScreen.fxml",btn_update,600,400);
        FXMLLoader fxmlLoader = new FXMLLoader();
    }
}
