package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.io.InputStream;

public class LoginView {

    public TextField txtfld_username;
    public TextField txtfld_password;
    public Button btn_login;
    public Hyperlink hpl_readUser;
    public Hyperlink hpl_createUser;

    public String getUserName(){
        return  txtfld_username.getText();
    }

    public String getPassword(){
        return  txtfld_password.getText();
    }


    private void loadCreateForm(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource("/create.fxml").openStream();
            Parent createForm = fxmlLoader.load(is);
            Scene newScene = new Scene(createForm,600,400);
            Stage curStage = (Stage) hpl_createUser.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleCreateLink(ActionEvent actionEvent) {loadCreateForm();}

}
