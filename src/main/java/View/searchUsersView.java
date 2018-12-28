package View;

import vacationClasses.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * In this view, the user can search for details of a registered user.
 */
public class searchUsersView extends AView {
    public Button btn_returnHome;
    public TextField txtfld_searchedUser;

    public void handleReturnButton(){loadLoginScreen();}

    /**
     * loads the login screen after a request to return to the home page is invoked.
     */
    public void loadLoginScreen(){
        openNewWindow("/login.fxml", btn_returnHome, 600,400);
    }
    /**
     *Handling with a search request.
     */
    public void handleSearchButton(){
        String userToSearch = txtfld_searchedUser.getText();
        User returnedUser= myController.searchAndReadUser(userToSearch);
        if(returnedUser == null)
            displayInformationMessage("No such user in the system","Search returned with 0 results");
        else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                InputStream is = this.getClass().getResource("/searchUsersResult.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView searchUsersResultView = fxmlLoader.getController();
                searchUsersResultView.setMyController(this.myController);
                Scene newScene = new Scene(actionScreen, 600, 400);
                Stage curStage = (Stage) btn_returnHome.getScene().getWindow();
                curStage.setScene(newScene);
                ((searchUsersResultView)searchUsersResultView).showDeatilesOfUser(returnedUser);
                curStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void loadSearchResultLogin(){
        String userToSearch = txtfld_searchedUser.getText();
        User returnedUser= myController.searchAndReadUser(userToSearch);
        if(returnedUser == null)
            displayInformationMessage("No such user in the system","Search returned with 0 results");
        else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                InputStream is = this.getClass().getResource("/searchUserResultLogin.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView searchUsersResultView = fxmlLoader.getController();
                searchUsersResultView.setMyController(this.myController);
                Scene newScene = new Scene(actionScreen, 600, 400);
                Stage curStage = (Stage) btn_returnHome.getScene().getWindow();
                curStage.setScene(newScene);
                ((searchUsersResultView)searchUsersResultView).showDeatilesOfUser(returnedUser);
                curStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void loadActionForm(){
        openNewWindow("/actionScreen.fxml", btn_returnHome , 600,450);

    }

}
