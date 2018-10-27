package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.awt.*;

public class LoginScreenController {

    public Hyperlink hpl_createUser;

    private void loadCreateForm(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            Parent createForm = (AnchorPane)fxmlLoader.load(getClass().getResource("/create.fxml").openStream());
            Scene newScene = new Scene(createForm,600,400);
            Stage curStage = (Stage) hpl_createUser.getScene().getWindow();
            curStage.setScene(newScene);

        }
        catch (Exception e){
          e.printStackTrace();
        }
    }

    public void handleCreateLink(ActionEvent actionEvent) {loadCreateForm();}

}
