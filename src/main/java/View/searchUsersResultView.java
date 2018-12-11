package View;

import vacationClasses.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *In this view, the user can see the details of the searched user.
 */
public class searchUsersResultView extends AView {

    public TextField txtfld_newUsername;
    public TextField txtfld_dateOfBirth;
    public TextField txtfld_firstName;
    public TextField txtfld_lastName;
    public TextField txtfld_residence;
    @FXML
    Button btn_returnHome;
    @FXML
    Button btn_search;

    /**
     * This function pull the details of the given username and presents them in the search result screen.
     * @param userName - an object containing all the details of the requested user.
     */
    public void showDeatilesOfUser(User userName){
        txtfld_newUsername.setText(userName.getUsername());
        txtfld_firstName.setText(userName.getFirstname());
        txtfld_lastName.setText(userName.getLastname());
        txtfld_dateOfBirth.setText(userName.getBirthdate());
        txtfld_residence.setText(userName.getResidence());


    }
    public void loadActionForm(){
        openNewWindow("/actionScreen.fxml", btn_returnHome , 600,400);

    }
    public void loadSearchFormFromAction() {
        openNewWindow("/searchUserAction.fxml", btn_search,600,400);
    }

    public void loadSearchFormFromLogin() {
        openNewWindow("/searchUsersLogin.fxml", btn_search,600,400);
    }
    public void loadLoginScreen(){
        openNewWindow("/login.fxml", btn_returnHome, 500,400);
    }
}
