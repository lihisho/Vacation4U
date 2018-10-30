package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.io.InputStream;

public class loginView extends AView{

    public TextField txtfld_username;
    public TextField txtfld_password;
    public Button btn_login;
    public Hyperlink hpl_readUser;
    public Hyperlink hpl_createUser;

    /**
     * getter for the given username.
     * @return the username entered by the user.
     */

    public String getUserName(){
        return  txtfld_username.getText();
    }

    /**
     * setter for the given password.
     * @return the password entered by the user.
     */

    public String getPassword(){
        return  txtfld_password.getText();
    }

    public void handleCreateLink(ActionEvent actionEvent) {loadCreateForm();}

    private void loadCreateForm(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource("/create.fxml").openStream();
            Parent createForm = fxmlLoader.load(is);
            createView create =fxmlLoader.getController();
            create.setMyController(this.myController);
            Scene newScene = new Scene(createForm,600,600);
            Stage curStage = (Stage) hpl_createUser.getScene().getWindow();
            curStage.setScene(newScene);
            create.setDeafultDate();
            curStage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * The login method first make sure that the user entered values to both of the required fields.
     * The user will arrive to a new screen where he can choose either to update his details, delete his user or search
     * for another user details.
     * @return true- if the login passed successfully. Otherwise- false.
     */
    public void login(){
        String user= this.getUserName();
        String pass =this.getPassword();
        try {
            isNotEmpty(user);
            isNotEmpty(pass);
            //if both username and password are not empty
            if(myController.login(this.getUserName(), this.getPassword()))
                openNewWindow("/actionScreen.fxml", btn_login);
            else
                displayErrorMessage("Username or password are incorrect. Please try again","Log in failed!");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function loads the screen in which the user can read details of a specific user.
     */
    public void handleSearchUserLink() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream is = this.getClass().getResource("/searchUsers.fxml").openStream();
            Parent actionScreen = fxmlLoader.load(is);
            AView searchUsersView = fxmlLoader.getController();
            searchUsersView.setMyController(this.myController);
            Scene newScene = new Scene(actionScreen, 600, 400);
            Stage curStage = (Stage) btn_login.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


