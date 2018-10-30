package View;

import Model.User;
import javafx.scene.control.TextField;

public class searchUsersResultView extends AView {

    public TextField txtfld_newUsername;
    public TextField txtfld_dateOfBirth;
    public TextField txtfld_firstName;
    public TextField txtfld_lastName;
    public TextField txtfld_residence;

    public void showDeatilesOfUser(User userName){
        txtfld_newUsername.setText(userName.getUsername());
        txtfld_firstName.setText(userName.getFirstname());
        txtfld_lastName.setText(userName.getLastname());
        txtfld_dateOfBirth.setText(userName.getBirthdate());
        txtfld_residence.setText(userName.getResidence());


    }
}
